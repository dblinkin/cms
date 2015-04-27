<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>陆良农村产权交易所</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
 
    <!-- Custom styles for this template -->
	  <script src="/js/jquery-2.1.3.min.js"></script>
	  <script src="/js/bootstrap.min.js"></script>
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <script src="/js/html5shiv.min.js"></script>
      <script src="/js/respond.min.js"></script>
	  <script src="/js/date.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script type="text/javascript">
	$('.carousel').carousel({
  interval: 2000
});
	</script>
 <style>

li{
	margin-top:0.5em;
}
th{
font-weight: normal;
}
td{
padding: 0.4em;
}
a {
color:#333333;
}
.nav-pills>li.active>a, .nav-pills>li.active>a:focus, .nav-pills>li.active>a:hover {
  color: #337AB7;
  cursor: default;
  background-color: #fff;
  border: 2px solid #eaeaea;
  border-bottom-color: transparent;
  border:none;

  }
  

.nav>li:hover .dropdown-menu {display: block;}
.navbar .nav > li .dropdown-menu {
	margin: -1em;
}
.nav-tabs .dropdown-menu{
border-radius:10px 10px 10px 10px;
}
.panel-primary{	
margin-bottom:0px;
}
.panel-primary>.panel-heading {
  color: #fff;
  background-color: #337ab7;
  border-color: #337ab7;
}
.panel-title {
  margin-top: 0;
  margin-bottom: 0;
  font-size: 1.5em;
  color: #333;
  text-align:center;
}
/* 2 Menus */

.mega-menu-2 ul { list-style: none; margin: 0 10px 5px; float: left; width: 130px; }
.transition ul li a {
-webkit-transition: 0.2s ease-in-out; /** Chrome & Safari **/
-moz-transition: 0.2s ease-in-out; /** Firefox **/
-o-transition: 0.2s ease-in-out; /** Opera **/ }
.transition ul li a:hover {
padding-left: 3px; 
-webkit-transition: 0.2s ease-in-out; /** Chrome & Safari **/
-moz-transition: 0.2s ease-in-out; /** Firefox **/
-o-transition: 0.2s ease-in-out; /** Opera **/ }

.dropdown-menu{
	min-width:120px;
}

</style>
  </head>
<!--
  <body style="background-color:#fff;">
  <div class="ad" style="left: 0.5em; position: absolute; top: 10em;z-index:999;">
   <a title="项目路演" target="_blank" href=""><img src="image/ad_1.jpg"></a>
<br></div>
<div class="ad" style="right: 0.5em; position: absolute; top: 10em;z-index:999;">
   <a title="手机报征订" target="_blank" href=""><img src="image/ad_2.jpg"></a>
<br></div>
-->
       <div class="page-header" style="margin:0 9em;background-image:url(/image/logo-8.jpg);height:8.5em;">
    <div >
   
  
  <div  style="width:20em;height:4em;float:right;margin-right:1em;margin-top:0em;">
  <div style="margin-left:6em;">
<iframe allowtransparency="true" frameborder="0" width="195" height="36" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=3&z=2&t=0&v=0&d=3&bd=0&k=000000&f=&q=1&e=1&a=1&c=70878&w=180&h=36&align=right"></iframe>
    
	</div>
	<div class="input-group">
	<input type="text" class="form-control" placeholder="站内搜索">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">搜索</button>
      </span>
      
    </div>
  </div>

</div>

		</div>
		
		
		
    <div class="container-fluid" style="margin-top: 0.5em;">
	        <#--导航条开始-->
           <div class="row">
	 <div class="col-lg-12 col-md-12" style="text-align: center;font-family:'Micro YaHei',微软雅黑;padding-right:9em;padding-left:9em;">
				<div class="panel panel-primary" style="margin-bottom: 0px;">
			  <div class="panel-heading" style="padding:0" >
        <h3 class="panel-title" id="panel-title" style="font-size: 1.6em;">
		
		                <ul class="nav nav-pills  "  style="display: inline-block;float: none; border:none;margin-top:-0.3em;">
                           <#list channels as ch1>
                           <li class="dropdown " >
										
										
										<a  href="${ch1.channelUrl?if_exists}" target="_blank">
											${ch1.channelName} 
											<#if ch1.children?size != 0>
											<span class="caret"></span>
											</#if>
										</a>
										
										<#if ch1.children?size != 0>
										<ul class="dropdown-menu mega-menu-2 transition" style="margin-top:-0.01em;width:9.8em;">
										<ul style="margin-left:-1.5em;">
											<#list ch1.children as ch2>
											
											<li><a href="${ch2.channelUrl?if_exists}" target="_blank">${ch2.channelName}</a></li>
											
											</#list>
										</ul>
										
										</ul>
								</#if>		
							</li>
							
                            </#list>
						  

                       </ul>
					   </h3>
		     </div>
      </div>
    </div>

     <#--导航条结束-->
     </div>
<div class="row">
	
	<div class="col-lg-3 col-lg-offset-1">
	<ol class="breadcrumb" style="background-color:#fff;">
  <li ><a href="/index.html"  target="_blank">首页</a></li>
  
  
  <li><a href="${currentChannel.channelUrl?if_exists}"  target="_blank">${currentChannel.channelName}</a></li>
  <li><a href="${currentChannel2.channelUrl?if_exists}"  target="_blank">${currentChannel2.channelName}</a></li>
	
  </ol>
	
	
	</div>
	
	<div  class="col-lg-8" style="margin-top:1.0em;">
	<div class="row">
			<div id="currentDate"class="col-lg-6 col-lg-offset-5" style="text-align:center;"></div>
			</div>
	</div>
	
</div>

<div class="row">
	<div class="col-lg-10 col-lg-offset-1" style="margin-top: -1em;">
	<div class="panel panel-primary" style="">
			  <div class="panel-heading" style="background-color:#fff;padding:0; border-bottom: none;font-family:'Micro YaHei',微软雅黑;">
        <h3 class="panel-title" id="panel-title" style="padding-top:20px;" >${article.articleTitle}
		
		<div class="row"> 
			
				
				
				<div class="col-lg-12" style="margin-top:0.5em;">
				<ul class="nav nav-tabs">
 
				<li class="divider"></li>
			</ul>
				</div>
				
				<div class="col-lg-12" style="text-align:center;font-size:16px;">
				<p>来源：${article.articleSrc}&nbsp;&nbsp;时间:${article.releasedTime[0..10]}</p>
				</div>
		</div>
		
		</h3>
      </div>
     
      <div class="panel-body" style="">
	
		<div class="row">
				
				<div class="col-lg-10 col-lg-offset-1">
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			${article.articleContent}
			</p>
				</div>
	</div>
</div>
</div>
</div>
</div>


      <!-- Site footer -->
      <footer class="footer"  style="text-align:center; margin-top:0.5em;">
    
	<div style="margin-top:1.5em;">
	 <address>
		<strong>&copy; 2005-2015 陆良农交所 滇ICP备05070218号</strong></br>
		地址:云南曲靖陆良县
		<abbr title="Phone">电话:(010)123455678</abbr>
	 
	 </address>
	</div>
      </footer>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
	
		<script type="text/javascript">
	 $(function(){ 
var date = getCurrentDateTime(); 
var calendar = showCal(); 
var lunarYear = GetcDateString();
$("#currentDate").text(date + " 农历:" + calendar); 
});
	
	</script>
  </body>
</html>


