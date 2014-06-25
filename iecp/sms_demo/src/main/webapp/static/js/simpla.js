$(document).ready(function(){
	$("#main-nav li ul").hide(); // Hide all sub menus
	
	$("#main-nav li a.current").parent().find("ul").slideToggle("slow"); // Slide
																			// down
																			// the
																			// current
																			// menu
																			// item's
																			// sub
																			// menu
	
	$("#main-nav li a.nav-top-item").click( // When a top menu item is
											// clicked...
		function () {
			$(this).parent().siblings().find("a").removeClass('current');
			$(this).addClass('current')
			$(this).parent().siblings().find("ul").slideUp("normal"); // Slide
																		// up
																		// all
																		// sub
																		// menus
																		// except
																		// the
																		// one
																		// clicked
			$(this).next().slideToggle("normal"); // Slide down the clicked
													// sub menu
			
		}
	);
	
	$("#main-nav li ul li a").click( // When a top menu item is clicked...
		function () {
			$(this).parent().siblings().find("a").removeClass('current');
			$(this).addClass('current');
		}
	);
	
	$("#main-nav li a.no-submenu").click( // When a menu item with no sub menu
											// is clicked...
		function () {
			window.location.href=(this.href); // Just open the link instead of
												// a sub menu
			return false;
		}
	); 
	
	// Sidebar Accordion Menu Hover Effect:
	
	$("#main-nav li .nav-top-item").hover(
		function () {
			$(this).stop().animate({ paddingRight: "25px" }, 200);
		}, 
		function () {
			$(this).stop().animate({ paddingRight: "15px" });
		}
	);
	
	// Minimize Content Box
	
	$(".content-box-header h3").css({ "cursor":"s-resize" }); // Give the h3
																// in Content
																// Box Header a
																// different
																// cursor
	$(".closed-box .content-box-content").hide(); // Hide the content of the
													// header if it has the
													// class "closed"
	$(".closed-box .content-box-tabs").hide(); // Hide the tabs in the header
												// if it has the class "closed"
	
	$(".content-box-header h3").click( // When the h3 is clicked...
		function () {
			$(this).parent().next().toggle(); // Toggle the Content Box
			$(this).parent().parent().toggleClass("closed-box"); // Toggle
																	// the class
																	// "closed-box"
																	// on the
																	// content
																	// box
			$(this).parent().find(".content-box-tabs").toggle(); // Toggle
																	// the tabs
		}
	);
	
	// Content box tabs:
	
	$('.content-box .content-box-content div.tab-content').hide(); // Hide the
																	// content
																	// divs
	$('ul.content-box-tabs li a.default-tab').addClass('current'); // Add the
																	// class
																	// "current"
																	// to the
																	// default
																	// tab
	$('.content-box-content div.default-tab').show(); // Show the div with
														// class "default-tab"
	
	$('.content-box ul.content-box-tabs li a').click( // When a tab is
														// clicked...
		function() { 
			$(this).parent().siblings().find("a").removeClass('current'); // Remove
																			// "current"
																			// class
																			// from
																			// all
																			// tabs
			$(this).addClass('current'); // Add class "current" to clicked
											// tab
			var currentTab = $(this).attr('href'); // Set variable "currentTab"
													// to the value of href of
													// clicked tab
			$(currentTab).siblings().hide(); // Hide all content divs
			$(currentTab).show(); // Show the content div with the id equal to
									// the id of clicked tab
			return false; 
		}
	);
	
	// Close button:
	
	$(".close").click(
		function () {
			$(this).parent().fadeTo(400, 0, function () { // Links with the
															// class "close"
															// will close parent
				$(this).slideUp(400);
			});
			return false;
		}
	);
	
	// Alternating table rows:
	
	$('tbody tr:even').addClass("alt-row"); // Add class "alt-row" to even table
											// rows
	
	// Check all checkboxes when the one in a table head is checked:
	
	$('.check-all').click(
		function(){
			$(this).parent().parent().parent().parent().find("input[type='checkbox']").attr('checked', $(this).is(':checked'));
		}
	);
	
	// Initialise Facebox Modal window:
	
	// $('a[rel*=modal]').facebox(); // Applies modal window to any link with
	// attribute rel="modal"
	
	// Initialise jQuery WYSIWYG:
	
	$(".wysiwyg").wysiwyg(); // Applies WYSIWYG editor to any textarea with
								// the class "wysiwyg"

});

function isMobile(str) {
    var test_tell = /^1[3|5]\d{9}$/;
    if (test_tell.test(str)) {
        return true;
    } else {
        return false;
    }
}
function isValidDate(yyyyMMdd) {
    if (yyyyMMdd == null || yyyyMMdd == "") {
        return false;
    }
    var yyyy = yyyyMMdd.substring(0, 4);
    var mm = yyyyMMdd.substring(4, 6);
    var dd = yyyyMMdd.substring(6, 8);

    var int_yyyy = parseInt(yyyy, 10);
    var int_mm = parseInt(mm, 10);
    var int_dd = parseInt(dd, 10);

    if (isNaN(int_yyyy) || isNaN(int_mm) || isNaN(int_dd)) {
        return false;
    }
    if (int_yyyy > 2050 || int_yyyy < 1900)
        return false;
    if (int_mm > 12 || int_yyyy < 1)
        return false;
    if (int_dd > 31 || int_yyyy < 1)
        return false;
    if (int_mm == 2) {
        if (!(((int_yyyy % 4 == 0) && (int_yyyy % 100 != 0)) || (int_yyyy % 400 == 0))) {
            if (int_dd > 28) {
                return false;
            }
        } else {
            if (int_dd > 29) {
                return false;
            }
        }
    }
    if (int_mm == 4 || int_mm == 6 || int_mm == 9 || int_mm == 11) {
        if (int_dd > 30) {
            return false;
        }
    }
    return true;
}

function checkall(obj) {
    var objArr = document.all("checkbox");
    if (obj.checked) {
        for (var i = 0; i < objArr.length; i++) {
            objArr[i].checked = true;
        }
    }
    else {
        for (var i = 0; i < objArr.length; i++) {
            objArr[i].checked = false;
        }
    }
}

function copyText(obj) {
    var rng = document.body.createTextRange();
    rng.moveToElementText(obj);
    rng.scrollIntoView();
    rng.select();
    rng.execCommand("Copy");
    rng.collapse(false);
} 
  