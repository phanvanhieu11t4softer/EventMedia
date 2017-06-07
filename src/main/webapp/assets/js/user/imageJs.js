$("#search-image").click(function() {
	$("#searchForm").attr("action", "/EventMedia/searchImage");
	$("#searchForm").submit();
});

// paging
function nextPage(noPage) {
	$("#noPage").val(noPage);
	$("#searchForm").attr("action", "/EventMedia/searchImage");
	$("#searchForm").submit();
}

function prevPage(noPage) {
	$("#noPage").val(noPage);
	$("#searchForm").attr("action", "/EventMedia/searchImage");
	$("#searchForm").submit();
}

function goPageTotal(totalPage) {
	$("#noPage").val(totalPage);
	$("#searchForm").attr("action", "/EventMedia/searchImage");
	$("#searchForm").submit();
}

$("#noPage").blur(function() {
	$("#searchForm").attr("action", "/EventMedia/searchImage");
	$("#searchForm").submit();
});