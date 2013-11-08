<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<script type="text/javascript">
	$(function() {
		layout_west_tree = $('#layout_west_tree').tree({
			url : "webpage/tree_data1.json",			
			onClick : function(node) {
				if (node.attributes && node.attributes.url) {
					var url = node.attributes.url;
					/*$.messager.progress({
						title : '提示',
						text : 'Loading....'
					});*/

					addTab({
						url : url,
						title : node.text
					});
				}
			},
			onDblClick : function(node) { //双击展开
				
			},
			onBeforeLoad : function(node, param) {
				
			},
			onLoadSuccess : function(node, data) {
				$.messager.progress('close');
			}
		});
		
	});

	function addTab(params) {
		var iframe = '<div class="easyui-layout" data-options="fit:true,border:false">'
			+ '<div region="center" data-options="border:false" title="" style="overflow: hidden;">'
			+ '<iframe src="' + params.url + '" frameborder="0" style="width:100%;height:100%; " scrolling="no"></iframe>'
			+ '</div></div>';
		var t = $('#maintabs');
		var opts = {
			title : params.title,
			closable : true,
			content : iframe,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
			$.messager.progress('close');
		} else {
			t.tabs('add', opts);
		}
		
	}
</script>
<ul id="layout_west_tree" class="easyui-tree" data-options="animate:true,lines:true"></ul>


