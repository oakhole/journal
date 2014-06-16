package com.oakhole.common.util;

import java.util.ArrayList;
import java.util.List;

public class Paginator {

	public static List<Integer> paginator(int currentPage, int length,
			int pageCount) {

		List<Integer> result = new ArrayList<Integer>();

		if (currentPage > pageCount) {
			currentPage = pageCount;
		}

		int start = 0, end = 0;

		if (pageCount <= length) {
			start = 1;
			end = pageCount;
		} else {
			// if (currentPage + length - 1 >= pageCount) {
			// start = pageCount - length + 1;
			// end = pageCount;
			// } else {
			if (currentPage + (length / 2) >= pageCount) {
				start = pageCount - length + 1;
				end = pageCount;
			} else if (currentPage - (length / 2) <= 0) {
				start = 1;
				end = start + length - 1;
			} else {
				if (length % 2 == 0) {
					start = currentPage - length / 2 + 1;
					end = start + length - 1;
				} else {
					start = currentPage - length / 2;
					end = start + length - 1;
				}
			}
			// }
		}

		for (int i = start; i <= end; i++) {
			result.add(i);
		}
		return result;

	}
}
