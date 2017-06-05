// init
var frmName, frmDescription, frmDateStart, frmDateEnd, frmNote, frmStatus, frmType;

function getGroup() {
	$.ajax({
	    url : "/EventMedia/manager/init",
	    type : "GET",
	    data : false,
	    dataType : 'json',
	    async : true,
	    success : function(data, textStatus, jqXHR) {

	    	if (jqXHR.status == 404) {
		    	$("#messageContainer").removeClass('hidden_elem');
		    }
	    	if (jqXHR.status == 200) {

	    		// hiden button edit when status of group is inactive
	    		if (data.status == 0) {
	    			$("#divBtnEdit").html(
	    					"<input type='button' id='btnEdit' onclick='clickBtnEdit();' value='Edit' class='btn btn-default'>"
	    					+"<input type='button' id='btnSave' onclick='clickBtnSave();' value='Save' class='btn btn-default hidden_elem'>"
	    					+"  <input type='button' id='btnCancel' onclick='clickBtnCancel();' value='Cancel' class='btn btn-default hidden_elem'>");
	    			frmName = data.name;
	    			frmDescription = data.description;
	    			frmDateStart = data.dateStart;
	    			frmDateEnd = data.dateEnd;
	    			frmNote = data.note;
	    			frmStatus = data.status;
	    			frmType = data.type;
	    		}

		    	// group info
	    		$("#id").val(data.id);
	    		$("#name").val(data.name);
	    		$("#description").val(data.description);
	    		$("#note").val(data.note);
	    		$("#dateStart").val(data.dateStart);
	    		$("#dateEnd").val(data.dateEnd);
	    		
	    		$('#inactive').prop("checked", true);
	    		if (data.status == 0) {
	    			$('#active').prop("checked", true);
	    		}

	    		$('#public').prop("checked", true);
	    		if (data.type == 0) {
	    			$('#private').prop("checked", true);
	    		}

	    		// List member
	    		if (data.user.length > 0) {
		    		if ($.fn.DataTable
		                    .isDataTable('#dataTables-result')) {
		                $('#dataTables-result').DataTable()
		                        .destroy();
		            }
	
	                $('#dataTables-result').DataTable({
	                    "bProcessing" : true,
	                    "aaData" : data.user,
	                    "createdRow" : function(row, data, dataIndex) {
	                        $(row).addClass('gradeX');
	                    },
	                    "aoColumns" : [
	                    	{
	                            "mDataProp" : "username",
	                            "mRender" : function(data, type, row) {
	                                return "<a href='/EventMedia/manager/" + row.id + "'>"
	                                        + data+"</a>";
	                            },
	                        }, { "mDataProp" : "name"
	                        }, { "mDataProp" : "birthday"
	                        }, { "mDataProp" : "email"
	                        }, { "mDataProp" : "gender"
	                        }, { "mDataProp" : "phone"
	                        }, { "mDataProp" : "id",
	                        	"mRender": function(data, type, row) {
	                        		 return "<button onclick='clickRemoveUser("+data+",this)'>" +
	                        		 		"<img src='./assets/imgs/delete-record.png' style='height: 20px;'/>" +
	                        		 		"</button>";
	                            }
	                        }],
	                    responsive : true
	                });
	    		} else {
	    			if (data.status == 1) {
	    				$(".listMember").addClass('hidden_elem');
	    			}
		    			$(".listMemberBody").html("<b>No member!</b>");
	    		}

	    		// list image
	    		if (data.image.length < 1) {
	    			$(".listMemberBody").html("<b>No image!</b>");
	    			$(".listMemberBody").child("div").addClass('hidden_elem');
	    		}
	    		
	    		if ($.fn.DataTable
	                    .isDataTable('#dataTables-image')) {
	                $('#dataTables-image').DataTable()
	                        .destroy();
	            }

                $('#dataTables-image').DataTable({
                    "bProcessing" : true,
                    "aaData" : data.image,
                    "createdRow" : function(row, data, dataIndex) {
                        $(row).addClass('gradeX');
                    },
                    "aoColumns" : [
                    	{
                            "mDataProp" : "title"
                        }, { "mDataProp" : "userCreate"
                        }, { "mDataProp" : "votes.length"
                        }, { "mDataProp" : "id",
                        	"mRender": function(data, type, row) {
                        		 return "<button onclick='clickRemoveImage("+data+",this)'>" +
                        		 		"<img src='./assets/imgs/delete-record.png' style='height: 20px;'/>" +
                        		 		"</button>";
                            }
                        }, 
                        { "mDataProp" : "url",
                        	"mRender": function(data, type, row) {
	                       	return "<img src='"+data+"' style='height: 150px;'"
	                            +" data-to='"+data+"'"
	                             + " data-caption='Like: <b>"+row.votes.length +"</b><br>" +
	                             		"<b>Title</b>: "+row.title +
	                             "</b></u><br>Description: "+row.description+"' class='modallery'>";
                        	}
                        }],
                    responsive : true
                });
            	$(this).modallery({
            	    title: "<b>"+data.name +"</b>",
            	    navigate: true,
            	    arrows: true,
            	    keypress: true
            	  });
		    }
	    	
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	    	$("#messageContainer").removeClass('hidden_elem');
	    	$(".manageUser").addClass('hidden_elem');
	    }
	});
}

// edit
function clickBtnEdit() {
	isEditGroupForm(false);
}

function clickBtnCancel() {
	$("#name").val(frmName);
	$("#description").val(frmDescription);
	$("#note").val(frmNote);
	$("#dateStart").val(frmDateStart);
	$("#dateEnd").val(frmDateEnd);
	$('#active').prop("checked", true);
	$('#public').prop("checked", true);
	if (frmType == 0) {
		$('#private').prop("checked", true);
	}
	isEditGroupForm(true);
}

function clickBtnSave() {
	if ($("#EditGroupForm").valid()) {
		var postData = $("#EditGroupForm").serializeArray();
		var formURL = $("#EditGroupForm").attr("action");
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			dataType : 'json',
			async: true,
			success : function(data, textStatus, jqXHR) {
				if (jqXHR.status == 200) {
					isEditGroupForm(true);
					$('#messageUpdateFail').addClass('hidden_elem');
					$('#messageUpdateSuccess').removeClass('hidden_elem');
					if ($('input[type=radio][name=status]:checked').val() == 1) {
						getGroup();
						$("#btnEdit").addClass('hidden_elem');

					} else {
						frmName = $("#name").val();
		    			frmDescription = $("#description").val();
		    			frmNote = $("#note").val();
		    			frmDateEnd = $("#dateEnd").val();
		    			frmDateStart = $("#dateStart").val();
		    			frmType =  $('input[type=radio][name=type]:checked').val();
	    			}
				}
				else {
					$('#messageUpdateFail').removeClass('hidden_elem');
					$('#messageUpdateSuccess').addClass('hidden_elem');
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				if (jqXHR.status == 200) {
					isEditGroupForm(true);
					$('#messageUpdateFail').addClass('hidden_elem');
					$('#messageUpdateSuccess').removeClass('hidden_elem');
					if ($('input[type=radio][name=status]:checked').val() == 1) {
						getGroup();
						$("#btnEdit").addClass('hidden_elem');

					} else {
						frmName = $("#name").val();
		    			frmDescription = $("#description").val();
		    			frmNote = $("#note").val();
		    			frmDateEnd = $("#dateEnd").val();
		    			frmDateStart = $("#dateStart").val();
		    			frmType =  $('input[type=radio][name=type]:checked').val();
	    			}
				}
				else {
					$('#messageUpdateFail').removeClass('hidden_elem');
					$('#messageUpdateSuccess').addClass('hidden_elem');
				}
			}
		});
	
	}
}

// when Status change = inactive then show wanning
$('input[type=radio][name=status]').change(function () {
	if ($('input[type=radio][name=status]:checked').val() == 1) {
		$('<div></div>').appendTo('body')
		.html('<div><h6>You have chosen to close this group.<br>Are you sure?</h6></div>')
		.dialog({
		modal: true,
		title: 'Confirm!!!',
		zIndex: 10000,
		autoOpen: true,
		width: 'auto',
		resizable: false,
		buttons: {
		    Yes: function () {
		        $(this).dialog("close");
		    },
		    No: function () {

		        // Change status is  0
		    	$('#active').prop("checked", true);
		        $(this).dialog("close");
		    }
		},
		close: function (event, ui) {
		    $(this).remove();
		}
		});
		
	}
});

// datepicker
var dateFormat = 'yy-mm-dd';
$("#dateStart").datepicker({
	dateFormat : dateFormat,
	minDate : '0d',
	maxDate : '+7d',
	onSelect : function() {
		if ($(this).valid()) {
			$("#dateEnd-error").hide();
		};
	}
});
$("#dateEnd").datepicker({
	dateFormat : dateFormat,
	minDate : new Date(),
	maxDate : '+30d',
	onSelect : function() {
		if ($(this).valid()) {
			$("#dateStart-error").hide();
		};
	}
});

// validate
jQuery.validator.addMethod("greaterThan", 
		function(value, element, params) {
		    if (!/Invalid|NaN/.test(new Date(value))) {
		        return new Date(value) >= new Date($(params[0]).val());
		    }
		    return isNaN(value) && isNaN($(params[0]).val()) 
		        || (Number(value) >= Number($(params[0]).val())); 
}, '{1} not greater than {2}.');

jQuery.validator.addMethod("lesserThan", 
		function(value, element, params) {
		    if (!/Invalid|NaN/.test(new Date(value))) {
		        return new Date(value) <= new Date($(params[0]).val());
		    }
		    return isNaN(value) && isNaN($(params[0]).val()) 
		        || (Number(value) <= Number($(params[0]).val())); 
}, '{1} not lesser than {2}.');

// check format date
jQuery.validator.addMethod("isDate", 
	function isDate(value, element) {
		var currVal = value;
	    if(currVal == '')
	        return false;
	    
	    var rxDatePattern = /^(\d{4})(\/|-)(\d{1,2})(\/|-)(\d{1,2})$/; //Declare Regex
	    var dtArray = currVal.match(rxDatePattern); // is format OK?
	    
	    if (dtArray == null) 
	        return false;

	    //Checks for mm/dd/yyyy format.
	    dtYear = dtArray[1];
	    dtMonth= dtArray[3];
	    dtDay = dtArray[5];

	    if (dtMonth < 1 || dtMonth > 12) 
	        return false;
	    else if (dtDay < 1 || dtDay> 31) 
	        return false;
	    else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31) 
	        return false;
	    else if (dtMonth == 2) 
	    {
	        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	        if (dtDay> 29 || (dtDay ==29 && !isleap)) 
	                return false;
	    }
	    return true;
}, '{0} is invalid format date.');

//validate register form
jQuery.extend(jQuery.validator.messages, {
	required : "This is item required"
});

//Setup form validation on the #update-form element
var validator = $("#EditGroupForm").validate({
	 errorElement: 'div',
	// Specify the validation rules
	rules : {
		name :  "required",
		description : "required",
		dateEnd : {
			required: true,
			isDate: ["Date end"],
			greaterThan: ["#dateStart", "Date start", "Date end"]
		},
		dateStart: {
			required: true,
			isDate: ["Date start"],
			lesserThan: ["#dateEnd", "Date end", "Date start"]
		},
	},
	errorPlacement : function(error, element) {
		if (element.is(":radio")) {
			error.appendTo(element.parents('.form-group'));
		} else { // This is the default behavior 
			error.insertAfter(element);
		}
	}
});

function isEditGroupForm(flgUpdate) {
	// item input
	$("#name").prop('disabled', flgUpdate);
	$("#description").prop('disabled', flgUpdate);
	$("#note").prop('disabled', flgUpdate);
	$("#dateStart").prop('disabled', flgUpdate);
	$("#dateEnd").prop('disabled', flgUpdate);
	$("input[type=radio][name=status]").prop("disabled",flgUpdate);
	$("input[type=radio][name=type]").prop("disabled",flgUpdate);

	if (flgUpdate) {

		// item button
		$("#btnEdit").removeClass('hidden_elem');
		$("#btnSave").addClass('hidden_elem');
		$("#btnCancel").addClass('hidden_elem');

		$("#name").removeClass('css-required');
		$("#description").removeClass('css-required');
		$("#dateStart").removeClass('css-required');
		$("#dateEnd").removeClass('css-required');
		$("#name-error").hide();
		$("#description-error").hide();
		$("#note-error").hide();
		$("#dateStart-error").hide();
		$("#dateEnd-error").hide();
	} else {
		$("#btnEdit").addClass('hidden_elem');
		$("#btnSave").removeClass('hidden_elem');
		$("#btnCancel").removeClass('hidden_elem');

		$("#name").addClass('css-required');
		$("#description").addClass('css-required');
		$("#dateStart").addClass('css-required');
		$("#dateEnd").addClass('css-required');
	}
}