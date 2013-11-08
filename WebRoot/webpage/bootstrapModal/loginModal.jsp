<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h4 id="myModalLabel">BPMIS_CMS 简介</h4>
	  </div>
      <div class="modal-body">
    <p style="text-indent:2em;">BPMIS（Backstage permission management information system）简称后台权限管理系统，其功能权限的管理技术，基于角色访问控制技术RBAC（Role Based Access Control）。</p>
    <p class="text-right"><strong>开发者：panxiaochao</strong></p>
  </div>
      <div class="modal-footer">
    <button class="btn btn-inverse" data-dismiss="modal" aria-hidden="true">关闭</button>
    <!--
<button class="btn btn-primary">Save changes</button>--> 
  </div>
    </div>
<!-- 反馈 -->
<div id="fankui" class="modal hide fade"  role="dialog" >
      <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">反馈</h4>
  </div>
      <div class="modal-body">
    <form class="form-horizontal">
          <div class="control-group"> 
        
        <!-- Text input-->
        <label class="control-label" for="input01">您的邮箱：</label>
        <div class="controls">
              <input type="text" placeholder="your email" id="email" />
            </div>
      </div>
          <div class="control-group"> 
        
        <!-- Textarea -->
        <label class="control-label">您的意见：</label>
        <div class="controls">
              <div class="textarea">
            <textarea rows="6" style="width:300px;" id="sug" ></textarea>
          </div>
            </div>
      </div>
        </form>
  </div>
      <div class="modal-footer">
    <button class="btn btn-primary" onclick="return fankui()">提交</button>
  </div>
</div>


