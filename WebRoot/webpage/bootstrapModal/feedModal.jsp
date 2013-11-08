<%@ page pageEncoding="utf-8"%>

<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	
	<div class="modal-body">
		<p>
			回馈内容：
		</p>
		<form name="feedm" action="" id="feedm">
			<textarea name="content" id="contentm"
				style="width: 505px; height: 150px;"></textarea>
			<input type="hidden" name="id" id="sid" />
		</form>
	</div>
	<div class="modal-footer">
		<input class="btn btn-primary" type="button" value="提交"
					onclick="return tijiao();" />
		<button class="btn" data-dismiss="modal"
			aria-hidden="true">
			关闭
		</button>
	</div>
</div>
<script type="text/javascript">
function feed(sid) {
	$("#myModal").modal('show');
	document.getElementById("sid").value = sid;
}

function tijiao() {
	var id = document.getElementById("sid").value;
	var content = document.getElementById("contentm").value;
	if (document.getElementById("contentm").value == "") {
		jinfo("请填写回馈内容！", "提示");
		return false;
	} else {
		jtip("发送中......", "loading");
		window.location.href = "systemController.do?sendFeed&id=" + id
				+ "&content=" + content;
		//$("#feedm").attr("action","systemController.do?sendFeed");
		//$("#feedm").submit();
		//jtip("发送成功！","success");
	}
	return false;
}
</script>

