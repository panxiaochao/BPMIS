<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="bpmis.pxc.system.pojo.TBUser"%>
<%@page import="org.pxcbpmisframework.core.util.ContextHolderUtils"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BPMIS_CMS</title>
<%@include file="/webpage/top_tld/easyui_tld.jsp"%>
<!--custom-->
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/customeasyui.css">
<script type="text/javascript">
	$(function() {		 		
		$('#pp').portal({
				border:false,
				fit:true
		});
		
		panels = [ {
			id : 'p1',
			title : '服务器运行状况',
			height : 300,
			collapsible : true

		}, {
			id : 'p2',
			title : '日志',
			height : 200,
			collapsible : true

		},  {
			id : 'p4',
			title : '系统配置',
			height : 150,
			collapsible : true

		}, {
			id : 'p5',
			title : '日期',
			height : 150,
			collapsible : true
		},{
			id : 'p6',
			title : '关于',
			height : 100,
			collapsible : true
		}];
		var state = 'p1,p2:p4,p5,p6';/*冒号代表列，逗号代表行*/

		addPortalPanels(state);
	});
	function getPanelOptions(id) {
		for ( var i = 0; i < panels.length; i++) {
			if (panels[i].id == id) {
				return panels[i];
			}
		}
		return undefined;
	}
	function getPortalState() {
		var aa = [];
		for ( var columnIndex = 0; columnIndex < 2; columnIndex++) {
			var cc = [];
			var panels = portal.portal('getPanels', columnIndex);
			for ( var i = 0; i < panels.length; i++) {
				cc.push(panels[i].attr('id'));
			}
			aa.push(cc.join(','));
		}
		return aa.join(':');
	}
	function addPortalPanels(portalState) {
		var columns = portalState.split(':');
		for ( var columnIndex = 0; columnIndex < columns.length; columnIndex++) {
			var cc = columns[columnIndex].split(',');
			for ( var j = 0; j < cc.length; j++) {				
				var options = getPanelOptions(cc[j]);
				if (options) {
					var p = $('<div/>').attr('id', options.id).appendTo('body');
					p.panel(options);
					$('#pp').portal('add', {
						panel : p,
						columnIndex : columnIndex
					});
				}
			}
		}
		$('#pp').portal('resize');
	}		
</script>

</head>

<body id="index_layout" class="easyui-layout">
<!--header-->
<div id="navhead" data-options="region:'north'" border="false" style="height:80px;"> <img src="images/logo.png"  /> </div>
<!--footer-->
<div data-options="region:'south'" border="false" style="height:30px;">
  <div id="footer"> Copyright © 2013-2014 BPMIS_CMS - Powered By <a href="#"  >Panxiaochao</a> V1.0 All Rights Reserved. </div>
</div>
<!--导航-->
<div id="west" data-options="region:'west',split:true" href="webpage/bg_tree.jsp" title="导航菜单" style="width:200px;"></div>
<!--主体-->
<div data-options="region:'center'"  >
  <div id="maintabs" class="easyui-tabs" fit="true" border="false" >
    <div title="首页" >
      <div id="pp" style="position:relative" style="overflow:hidden;" >
        <div style="width:70%" ></div>
        <div style="width:30%"></div>
    </div>
    </div>
    <div title="Help" data-options="iconCls:'icon-help',closable:true" style="padding:10px"> This is the help content. </div>
    <div title="Iframe" data-options="closable:true" style="overflow:hidden">
      <iframe scrolling="yes" frameborder="0"  src="http://www.baidu.com" style="width:100%;height:100%;"></iframe>
    </div>
  </div>
</div>
<div id="winForm" style="overflow:hidden;">  
<iframe scrolling="yes" id="pframe" frameborder="0" src="" style="width:100%;height:100%;"></iframe>   
</div>
</body>
</html>