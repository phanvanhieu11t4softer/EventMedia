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
		    	// group info
	    		$("#id").val(data.id);
	    		$("#name").val(data.name);
	    		$("#description").val(data.description);
	    		$("#note").val(data.note);
	    		$("#dateStart").val(data.dateStart);
	    		$("#dateEnd").val(data.dateEnd);
	    		
	    		$('#inactive').prop("checked", true)
	    		if (data.status == 0) {
	    			$('#active').prop("checked", true)
	    		}

	    		$('#public').prop("checked", true)
	    		if (data.type == 0) {
	    			$('#private').prop("checked", true)
	    		}
	    		

	    		// List member
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
                
                // list image
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
                            "mDataProp" : "title",
                            "mRender" : function(data, type, row) {
                                return "<a href='/EventMedia/manager/" + row.id + "'>"
                                        + data+"</a>";
                            },
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
	                       		return "<img src='"+data+"' style='height: 150px;'/>";
                        	}
                        }],
                    responsive : true
                });

		    }
	    	
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	    	$(".bg_white").addClass('hidden_elem');
	    }
	});
}