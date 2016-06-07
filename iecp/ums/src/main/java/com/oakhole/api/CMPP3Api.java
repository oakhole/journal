package com.oakhole.api;

import com.oakhole.adapter.SocketAdapter;
import com.oakhole.authenticate.CMPP3Authenticate;
import com.oakhole.connection.CMPPConnection;
import com.oakhole.packet.cmpp.*;
import com.oakhole.sequence.SimpleSequence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CMPP3Api {

    private static Log logger = LogFactory.getLog(CMPP3Api.class);

    private CMPPConnection connection;
    private SocketAdapter adapter;
    private CMPP3Authenticate authenticate;

    private SimpleSequence sequence;

    private boolean authenticated = false;

    public CMPP3Api() {
        this.adapter = new SocketAdapter();
        this.connection = new CMPPConnection();
        this.sequence = new SimpleSequence();
        this.authenticate = new CMPP3Authenticate();
        this.authenticated = false;
    }

    public CMPP3Api(String SP_Id, String shared_secret, String SP_Code,
                    String Service_Id) {

        this.connection = new CMPPConnection();
        this.sequence = new SimpleSequence();

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("SP_Id", SP_Id);
        parameters.put("shared_secret", shared_secret);
        parameters.put("SP_Code", SP_Code);
        parameters.put("Service_Id", Service_Id);
        this.authenticate = new CMPP3Authenticate(parameters);
    }

    /**
     * 连接
     *
     * @param remoteAddress
     * @param port
     * @return
     * @throws IOException
     */
    public synchronized boolean connect(String remoteAddress, int port)
            throws IOException {
        this.adapter = new SocketAdapter(remoteAddress, port);
        this.adapter.setTimeout(5 * 1000L);
        this.connection.open(adapter);
        return login();
    }

    /**
     * �ͷż�Ȩ�ر�����
     *
     * @return
     * @throws IOException
     */
    public boolean close() throws IOException {
        if (this.connection != null) {
            if (!this.connection.isClosed()) {
                if (!this.connection.isTerminate()) {
                    this.logout();
                }
                this.connection.close(this.adapter);
            }
            logger.info(this.adapter.getRemoteHost() + ":"
                    + this.adapter.getPort() + " logout");
            return true;
        }

        return false;
    }

    /**
     * ��֤�����Ƿ����
     *
     * @return
     */
    public synchronized boolean available() {
        return this.connection != null && this.connection.available();
    }

    /**
     * ��֤���Ӽ�Ȩ�Ƿ���Ч
     *
     * @return
     */
    public boolean isOpen() {
        return this.connection != null && this.authenticated;
    }

    /**
     * ��½��Ȩ
     *
     * @return
     * @throws IOException
     */
    private boolean login() throws IOException {
        CMPP_CONNECT cmpp_connect = new CMPP_CONNECT();

        cmpp_connect.setCommand_Id(CMPPCommandID.CONNECT);
        cmpp_connect.setSequence_Id(this.nextSequence());

        cmpp_connect.setSource_Addr((String) this.authenticate.getParameters()
                .get("SP_Id"));
        int timestamp = Integer.parseInt(new SimpleDateFormat("MMddHHmmss")
                .format(new Date()));
        cmpp_connect.setAuthenticatorSource(this.authenticate
                .getAuthenticatorSP(timestamp));
        cmpp_connect.setVersion((byte) 0x30);
        cmpp_connect.setTimestamp(timestamp);

        this.writePacket(cmpp_connect);
        CMPPPacket packet = this.readPacket();

        if (packet == null) {
            logger.error("Connection was break");
            return false;
        }

        if (packet instanceof CMPP_CONNECT_RESP) {
            CMPP_CONNECT_RESP cmpp_connect_resp = (CMPP_CONNECT_RESP) packet;
            if (cmpp_connect_resp.getStatus() == 0
                    && cmpp_connect_resp.getSequence_Id() == cmpp_connect
                    .getSequence_Id()) {
                this.authenticated = true;
                logger.info(this.adapter.getRemoteHost() + ":"
                        + this.adapter.getPort() + " login");
                return true;
            } else {
                logger.error(cmpp_connect.getSource_Addr()
                        + " authenticate failed,"
                        + cmpp_connect_resp.getStatus() + ":"
                        + CMPPStatus.toString(cmpp_connect_resp.getStatus()));
            }
        }

        return false;
    }

    /**
     * �˳���Ȩ
     *
     * @return
     * @throws IOException
     */
    private boolean logout() throws IOException {
        CMPP_TERMINATE cmpp_terminate = new CMPP_TERMINATE();

        cmpp_terminate.setCommand_Id(CMPPCommandID.TERMINATE);
        cmpp_terminate.setSequence_Id(this.nextSequence());

        this.writePacket(cmpp_terminate);
        CMPPPacket packet = this.readPacket();

        if (packet == null) {
            return false;
        }

        if (packet instanceof CMPP_TERMINATE) {
            CMPP_TERMINATE terminate = (CMPP_TERMINATE) packet;
            CMPP_TERMINATE_RESP cmpp_terminate_resp = new CMPP_TERMINATE_RESP();
            cmpp_terminate_resp.setCommand_Id(CMPPCommandID.TERMINATE_RESPONSE);
            cmpp_terminate_resp.setSequence_Id(terminate.getSequence_Id());
            this.writePacket(cmpp_terminate_resp);
            this.authenticated = false;
            return true;
        }

        if (packet instanceof CMPP_TERMINATE_RESP) {
            CMPP_TERMINATE_RESP cmpp_terminate_resp = (CMPP_TERMINATE_RESP) packet;
            if (cmpp_terminate_resp.getSequence_Id() == cmpp_terminate
                    .getSequence_Id()) {
                this.authenticated = false;
                return true;
            }
        }
        return false;
    }

    /**
     * ���Ӳ���
     *
     * @return
     * @throws IOException
     */
    public boolean active_test() throws IOException {
        CMPP_ACTIVE_TEST cmpp_active_test = new CMPP_ACTIVE_TEST();
        cmpp_active_test.setCommand_Id(CMPPCommandID.ACTIVETEST);
        cmpp_active_test.setSequence_Id(this.nextSequence());
        this.writePacket(cmpp_active_test);
        CMPPPacket packet = this.readPacket();

        if (packet instanceof CMPP_ACTIVE_TEST) {
            CMPP_ACTIVE_TEST_RESP active_test_resp = new CMPP_ACTIVE_TEST_RESP();
            active_test_resp.setCommand_Id(CMPPCommandID.ACTIVETEST_RESPONSE);
            active_test_resp.setSequence_Id(packet.getSequence_Id());
            active_test_resp.setReserved((byte) 0);
            this.writePacket(active_test_resp);
            return true;
        }

        if (packet instanceof CMPP_ACTIVE_TEST_RESP) {
            CMPP_ACTIVE_TEST_RESP active_test_resp = (CMPP_ACTIVE_TEST_RESP) packet;
            if (active_test_resp.getSequence_Id() == cmpp_active_test
                    .getSequence_Id()) {
                logger.info("active_test_success");
                return true;
            }
        }

        return false;
    }

    /**
     * ��ȡ��һ��sequence_id
     *
     * @return
     */
    public int nextSequence() {
        return this.sequence == null ? 0 : this.sequence.nextInteger();
    }

    public synchronized void writePacket(CMPPPacket packet) throws IOException {
        this.connection.writePacket(packet);
    }

    public synchronized CMPPPacket readPacket() throws IOException {
        return (CMPPPacket) this.connection.readPacket(new CMPPPacket() {
        });
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.connection = null;
        this.authenticate = null;
        this.sequence = null;
    }

    public CMPPConnection getConnection() {
        return connection;
    }

    public void setConnection(CMPPConnection connection) {
        this.connection = connection;
    }

    public SocketAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(SocketAdapter adapter) {
        this.adapter = adapter;
    }

    public CMPP3Authenticate getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(CMPP3Authenticate authenticate) {
        this.authenticate = authenticate;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public SimpleSequence getSequence() {
        return sequence;
    }

    public void setSequence(SimpleSequence sequence) {
        this.sequence = sequence;
    }

}
