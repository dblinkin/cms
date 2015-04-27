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
  font-size: 16px;
  color: #333;
}


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

.tjxmtdsize{width:236px; height:150px;}
.tjxmimg{width:219px; height:150px; margin:10px 17px 8px 0;border:0px solid #bababa; }
.tjxmline{font-size:14px;font-family:"微软雅黑";height:30px; line-height:30px;padding:0;}
a.tjxmdesc:link{font-size:14px;font-family:"微软雅黑";text-decoration:none;color:#000;}
a.tjxmdesc:visited{font-size:14px;font-family:"微软雅黑";text-decoration:none;color:#000; }
a.tjxmdesc:hover{font-size:14px;font-family:"微软雅黑";text-decoration:none;color:#000; }


</style>
  </head>
<!--
  <body style="background-color:#fff;">
  <div class="ad" style="left: 0.5em; position: absolute; top: 10em;z-index:999;">
   <a title="项目路演" target="_blank" href=""><img src="/image/ad_1.jpg"></a>
<br></div>
<div class="ad" style="right: 0.5em; position: absolute; top: 10em;z-index:999;">
   <a title="手机报征订" target="_blank" href=""><img src="/image/ad_2.jpg"></a>
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
										
										
										<a  href="${ch1.channelUrl?if_exists}" >
											${ch1.channelName} 
											<#if ch1.children?size != 0>
											<span class="caret"></span>
											</#if>
										</a>
										
										<#if ch1.children?size != 0>
										<ul class="dropdown-menu mega-menu-2 transition" style="margin-top:-0.01em;width:9.8em;">
										<ul style="margin-left:-1.5em;">
											<#list ch1.children as ch2>
											
											<li><a href="${ch2.channelUrl?if_exists}" >${ch2.channelName}</a></li>
											
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
	
	
	
	  <div class="row" style="margin-top:0.5em;">
		
		
			  <div class="col-lg-6  col-md-6 col-lg-offset-1 col-md-offset-1" >
       <div id="carousel-news" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="/image/1.jpg" alt="...">
      <div class="carousel-caption">
        测试
      </div>
    </div>
     <div class="item">
      <img src="/image/2.jpg" alt="...">
      <div class="carousel-caption">
        测试
      </div>
    </div>
	 <div class="item">
      <img src="/image/3.jpg" alt="...">
      <div class="carousel-caption">
        测试
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-news" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-news" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
	</div>
		<#--通知公告开始-->
		  <div class="col-lg-5 col-md-5" style="margin-left:-2em;padding-right:7em;padding-left:15px" >
		
          
              <div class="panel panel-primary" style="">
			  <div class="panel-heading" style="padding:0; border-bottom: none;font-family:'Micro YaHei',微软雅黑;">
        <h3 class="panel-title" id="panel-title" >
		   <ul class="nav nav-tabs " >
		   <#if notice_channel?size != 0>
		   <#list notice_channel as ch1>
		   <#if ch1_index = 0>
		<li  style="margin-top:0;"; class="active"><a href="#${ch1.channel.channelDesc}" aria-controls="${ch1.channel.channelDesc}" role="tab" data-toggle="pill" style="text-decoration:none;" >${ch1.channel.channelName}</a></li>
			<#else>
		<li  style="margin-top:0;";><a href="#${ch1.channel.channelDesc}" aria-controls="${ch1.channel.channelDesc}" role="tab" data-toggle="pill" style="text-decoration:none;" >${ch1.channel.channelName}</a></li>
			
			</#if>
			</#list>
			</#if>
				
			</ul>
			
			</h3>
      </div>
	  
     <div class="tab-content">
	
	 <#if notice_channel?size != 0>
	<#list notice_channel as notice>
	<#if notice_index = 0>
	 <div role="tabpanel" class="tab-pane active" id="${notice.channel.channelDesc}">
	 
      <div class="panel-body" style="margin-top:-1em;margin-left:-2em;margin-bottom:0.5em;position:relative;height:18.3em;">
			<ul style="margin-left:-1.5em;" >
			<#list notice.articleIndex as  article>
			<li style="width:25em;margin-top:1.3em;overflow-x: hidden;white-space: nowrap;text-overflow: ellipsis;list-style-position: inside;" >
				
				
				<a href="${article.articleUrl}" target="_blank" title="${article.articleTitle}">

					
					   
                 ${article.articleTitle}


				</a>
				<span style="right:.5em;position:absolute;">${article.releasedTime[0..10]}</span>
				</li>
		  </#list>
			</ul>
	    </div>
	
	</div>
	<#else>
		 <div role="tabpanel" class="tab-pane" id="${notice.channel.channelDesc}">
	 
      <div class="panel-body" style="margin-top:-1em;margin-left:-2em;margin-bottom:0.5em;position:relative;height:18.3em;">
			<ul style="margin-left:-1.5em;" >
			<#list notice.articleIndex as  article>
			<li style="width:25em;margin-top:1.3em;overflow-x: hidden;white-space: nowrap;text-overflow: ellipsis;list-style-position: inside;" >
				
				
				<a href="${article.articleUrl}" target="_blank" title="${article.articleTitle}">

					
					   
                 ${article.articleTitle}


				</a>
				<span style="right:.5em;position:absolute;">${article.releasedTime[0..10]}</span>
				</li>
		  </#list>
			</ul>
	    </div>
	
	</div>
	</#if>
	</#list>
	
	</#if>
	
	</div>
	
	
	</div>
		  </div>
		<#--通知公告结束-->
		
    </div>
    
    
	<div class="row" style="margin-top:0.5em;">
	<#--新闻动态开始-->
	<div class="col-lg-4 col-md-4 col-sm-5 col-xs-5 col-lg-offset-1 col-md-offset-1" style="padding-right:5px;" >
		 <div class="panel panel-primary" style="">
			  <div class="panel-heading" style="padding:0; border-bottom: none;font-family:'Micro YaHei',微软雅黑;">
        <h3 class="panel-title" id="panel-title" >
		   <ul class="nav nav-tabs " >
		   <#if news_channel?size != 0>
		   <#list news_channel as news>
		   <#if news_index = 0>
		<li  style="margin-top:0;"; class="active"><a href="#${news.channel.channelDesc}" aria-controls="${news.channel.channelDesc}" role="tab" data-toggle="pill" style="text-decoration:none;" >${news.channel.channelName}</a></li>
			<#else>
		<li  style="margin-top:0;";><a href="#${news.channel.channelDesc}" aria-controls="${news.channel.channelDesc}" role="tab" data-toggle="pill" style="text-decoration:none;" >${news.channel.channelName}</a></li>
			
			</#if>
			</#list>
			</#if>
				
			</ul>
			
			</h3>
      </div>
	  
     <div class="tab-content">
	
	 <#if news_channel?size != 0>
	<#list news_channel as news>
	<#if news_index = 0>
	 <div role="tabpanel" class="tab-pane active" id="${news.channel.channelDesc}">
	 
      <div class="panel-body" style="margin-top:-1em;margin-left:-2em;margin-bottom:0.5em;position:relative;height:16em;">
			
			<ul style="margin-left:-1.5em;">
			<#list news.articleIndex as  article>
			<#if article_index lt 5>
			<li style="width:24em;margin-top:1.3em;overflow-x: hidden;white-space: nowrap;text-overflow: ellipsis;list-style-position: inside;">
				
				
				<a href="${article.articleUrl}" target="_blank" title="${article.articleTitle}">${article.articleTitle}</a>
				<span style="right:0.5em;position:absolute;">${article.releasedTime[0..10]}</span>
				</li>
			</#if>
		  </#list>
			</ul>
	    </div>
	
	</div>
	<#else>
		 <div role="tabpanel" class="tab-pane" id="${news.channel.channelDesc}">
	 
      <div class="panel-body" style="margin-top:-1em;margin-left:-2em;margin-bottom:0.5em;position:relative;height:16em;">
			
			<ul style="margin-left:-1.5em;">
			<#list news.articleIndex as  article>
			<#if article_index lt 5>
			<li style="width:24em;margin-top:1.3em;overflow-x: hidden;white-space: nowrap;text-overflow: ellipsis;list-style-position: inside;">
				<a href="${article.articleUrl}" target="_blank" title="${article.articleTitle}">${article.articleTitle}</a>
				<span style="right:0.5em;position:absolute;">${article.releasedTime[0..10]}</span>
				</li>
				</#if>
		  </#list>
			</ul>
	    </div>
	
	</div>
	
	</#if>
	</#list>
	
	</#if>
	
	</div>
	
	
	</div>
          
		</div>
	<#--新闻动态结束-->
	
	
	
	<div class="col-lg-6 col-md-6" style="padding-left:5px;" >
		
          <div class="panel panel-primary">
      <div class="panel-heading" style="font-family:'Micro YaHei',微软雅黑;">
        <h3 class="panel-title" id="panel-title">出让项目<a class="anchorjs-link" href="#panel-title"><span class="anchorjs-icon"></span></a></h3>
      </div>
      
        		<table class="table table-hover table-condensed" style="margin-top:0.3em">
      <thead>
        <tr>
         
          <th>项目类型</th>
          <th>标的名称</th>
          <th>所在地区</th>
		  <th>金额</th>
		  <th>投资用途</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row">实物资产</th>
          <td>求租陆良县五百平米房屋</td>
          <td>陆良县</td>
          <td>未设定</td>
		   <td>个人画室</td>
        </tr>
      <tr>
          <th scope="row">一般农用地</th>
          <td>求租陆良县三百亩耕地</td>
          <td>陆良县</td>
          <td>价格面议</td>
		   <td>蔬菜种植</td>
        </tr> 
		<tr>
          <th scope="row">养殖用地</th>
          <td>求租陆良县五十亩养殖用地</td>
          <td>陆良县</td>
          <td>价格好说</td>
		   <td>畜禽养殖</td>
        </tr>
		<tr>
          <th scope="row">养殖用地</th>
          <td>求租陆良县五十亩养殖用地</td>
          <td>陆良县</td>
          <td>价格好说</td>
		   <td>畜禽养殖</td>
        </tr>
		<tr>
          <th scope="row">养殖用地</th>
          <td>求租陆良县五十亩养殖用地</td>
          <td>陆良县</td>
          <td>价格好说</td>
		   <td>畜禽养殖</td>
        </tr>
		<tr>
          <th scope="row">养殖用地</th>
          <td>求租陆良县五十亩养殖用地</td>
          <td>陆良县</td>
          <td>价格好说</td>
		   <td>畜禽养殖</td>
        </tr>
       
      </tbody>
    </table>
      </div>
    </div>
		 </div>
	
	
	
	<div class="row" style="margin-top:0.5em;">
	
	<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
	
	<div class="panel panel-primary" style="margin-bottom: 0px;">
      <div class="panel-heading" style="font-family:'Micro YaHei',微软雅黑;">
        <h3 class="panel-title" id="panel-title">推荐项目<a class="anchorjs-link" href="#panel-title"><span class="anchorjs-icon"></span></a></h3>
      </div>
	
		<div style="text-align:center;margin:0 auto;">
			
			
 <div id="demozt" style="overflow:hidden; width:1000px;border:0;margin-left: 3em; ">
  <table cellpadding="0" cellspacing="0" border="0" >
    <tr>
      <td id="demozt1" align="center">
   <table cellspacing="0" cellpadding="0" width="100%">
<tr>
<!--这里放组件 -->
<td>
   <td align="center" valign="top">
<table border="0" cellpadding="0" cellspacing="0" style="text-align: center;">
<tr>
<td class="tjxmtdsize"><a title="陆良某镇2万平米厂房" target="_blank" href=""><img src="/image/r1.jpg" class="tjxmimg"></a></td>
</tr>
<tr>
<td valign="top" class="tjxmline"><a title="陆良某镇2万平米厂房" target="_blank" href="" class="tjxmdesc">陆良某镇2万平米厂房</a></td>
</tr>
</table>
</td>

<td align="center" valign="top">
<table border="0" cellpadding="0" cellspacing="0" style="text-align: center;">
<tr>
<td class="tjxmtdsize"><a title="陆良某张山营镇200亩荒山" target="_blank" href=""><img src="/image/r2.jpg" class="tjxmimg"></a></td>
</tr>
<tr>
<td valign="top" class="tjxmline"><a title="陆良某张山营镇200亩荒山" target="_blank" href="" class="tjxmdesc">陆良某张山营镇200亩荒山</a></td>
</tr>
</table>
</td>


<td align="center" valign="top">
<table border="0" cellpadding="0" cellspacing="0" style="text-align: center;">
<tr>
<td class="tjxmtdsize"><a title="陆良某镇200亩山林" target="_blank" href=""><img src="/image/r3.jpg" class="tjxmimg"></a></td>
</tr>
<tr>
<td valign="top" class="tjxmline"><a title="陆良某镇200亩山林" target="_blank" href="" class="tjxmdesc">陆良某镇200亩山林</a></td>
</tr>
</table>
</td>


<td align="center" valign="top">
<table border="0" cellpadding="0" cellspacing="0" style="text-align: center;">
<tr>
<td class="tjxmtdsize"><a title="陆良某镇200亩果园" target="_blank" href=""><img src="/image/r4.jpg" class="tjxmimg"></a></td>
</tr>
<tr>
<td valign="top" class="tjxmline"><a title="陆良某镇200亩果园" target="_blank" href="" class="tjxmdesc">陆良某镇200亩果园</a></td>
</tr>
</table>
</td>


<td align="center" valign="top">
<table border="0" cellpadding="0" cellspacing="0" style="text-align: center;">
<tr>
<td class="tjxmtdsize"><a title="陆良某镇200亩果园" target="_blank" href=""><img src="/image/r5.jpg" class="tjxmimg"></a></td>
</tr>
<tr>
<td valign="top" class="tjxmline"><a title="陆良某镇200亩果园" target="_blank" href="" class="tjxmdesc">陆良某镇200亩果园</a></td>
</tr>
</table>
</td>


<td align="center" valign="top">
<table border="0" cellpadding="0" cellspacing="0" style="text-align: center;">
<tr>
<td class="tjxmtdsize"><a title="陆良某镇200亩果园" target="_blank" href=""><img src="/image/r5.jpg" class="tjxmimg"></a></td>
</tr>
<tr>
<td valign="top" class="tjxmline"><a title="陆良某镇200亩果园" target="_blank" href="" class="tjxmdesc">陆良某镇200亩果园</a></td>
</tr>
</table>
</td>


</td>
</tr>
</table>
</td>
      <td id="demozt2" width="0"></td>
    </tr>
  </table>
</div>
</div>
	</div>
	</div>

</div>
	
      <!-- Example row of columns -->
      <div class="row" style="margin-top:0.5em" >
        <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1"  >
		
          <div class="panel panel-primary" style="margin-bottom:0em;">
      <div class="panel-heading" style="font-family:'Micro YaHei',微软雅黑;">
        <h3 class="panel-title" id="panel-title">挂牌项目<a class="anchorjs-link" href="#panel-title"><span class="anchorjs-icon"></span></a></h3>
      </div>
     
		
		<div class="row" style="margin-top:2em;">
		
		<div class="col-lg-2 col-md-2">
			<a href="#" ><img src="image/gp1.jpg" alt="" class="img-rounded img-responsive center-block"/><div class="caption text-center">农村土地</div></a>
		
		</div>
		<div class="col-lg-2 col-md-2">
			<a href="#" ><img src="image/gp2.jpg" alt="" class="img-rounded img-responsive center-block"/><div class="caption text-center">生产经营股权</div></a>
		
		</div>
		<div class="col-lg-2 col-md-2">
			<a href="#" ><img src="image/gp3.jpg" alt="" class="img-rounded img-responsive center-block"/><div class="caption text-center">实物资产</div></a>
		
		</div>
		<div class="col-lg-2 col-md-2">
			<a href="#" ><img src="image/gp4.jpg" alt="" class="img-rounded img-responsive center-block"/><div class="caption text-center">农业知识产权</div></a>
		
		</div>
		<div class="col-lg-2 col-md-2">
			<a href="#" ><img src="image/gp5.jpg" alt="" class="img-rounded img-responsive center-block"/><div class="caption text-center">农村经济事项</div></a>
		
		</div>
		<div class="col-lg-2 col-md-2">
			<a href="#" ><img src="image/gp6.jpg" alt="" class="img-rounded img-responsive center-block"/><div class="caption text-center">林权</div></a>
		
		</div>
		
		
		</div>
			
			
			
			
			
			<div class="row" style="margin-top:1em;">
		
		<div class="col-lg-12 col-md-12 ">
			
			<table class="table table-hover" style="margin-top:1em;">
      <thead>
        <tr>
         
          <th>项目编号</th>
          <th>标的名称</th>
          <th>项目类型</th>
		  <th>公告价格</th>
		  <th>挂牌时间</th>
		  <th>截止时间</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row">2015-PG/01-0001</th>
          <td>陆良大华山镇陈庄子村东南975亩荒山</td>
          <td>农村土地</td>
          <td>700</td>
		   <td>2015-04-03</td>
		    <td>2015-04-17</td>
        </tr>
      <tr>
          <th scope="row">2015-PG/01-0001</th>
          <td>陆良大华山镇陈庄子村东南975亩荒山</td>
          <td>农村土地</td>
          <td>700</td>
		   <td>2015-04-03</td>
		    <td>2015-04-17</td>
        </tr>
		<tr>
          <th scope="row">2015-PG/01-0001</th>
          <td>陆良大华山镇陈庄子村东南975亩荒山</td>
          <td>农村土地</td>
          <td>700</td>
		   <td>2015-04-03</td>
		    <td>2015-04-17</td>
        </tr>
		<tr>
          <th scope="row">2015-PG/01-0001</th>
          <td>陆良大华山镇陈庄子村东南975亩荒山</td>
          <td>农村土地</td>
          <td>700</td>
		   <td>2015-04-03</td>
		    <td>2015-04-17</td>
        </tr>
		<tr>
          <th scope="row">2015-PG/01-0001</th>
          <td>陆良大华山镇陈庄子村东南975亩荒山</td>
          <td>农村土地</td>
          <td>700</td>
		   <td>2015-04-03</td>
		    <td>2015-04-17</td>
        </tr>
		
       
      </tbody>
    </table>
    	</div>
    </div>
		
        </div>
	
      </div>
</div>


<div class="row" style="margin-top:0.5em;">
		<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
			<img src="image/ad.jpg" alt="" class="img-rounded img-responsive center-block"/>
		</div>
</div>

<div class="row" style="margin-top:0.5em;">
	<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
			
		<div class="col-lg-3 col-md-3  ">
			<a><img src="image/tu6.jpg" alt="" class="img-rounded img-responsive"/></a>
		</div>
		<div class="col-lg-3 col-md-3">
			<a><img src="image/tu7.jpg" alt="" class="img-rounded img-responsive"/></a>
		</div>
		<div class="col-lg-3 col-md-3">
			<a><img src="image/tu8.jpg" alt="" class="img-rounded img-responsive "/></a>
		</div>
		<div class="col-lg-3 col-md-3">
			<a><img src="image/tu9.jpg" alt="" class="img-rounded img-responsive "/></a>
		</div>
	</div>
</div>




      <!-- Site footer -->
      <footer class="footer"  style="text-align:center; margin-top:0.5em;">
      	<div class="row" >
      		<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1" style="text-align:center;">
		                <ul class="nav nav-tabs " >
						<li><a href="#" >友情链接</a></li>
						<li><a href="#" >云南农村委员会</a></li>
						<li><a href="#" > 云南联合产权交易所 </a></li>
						<li><a href="#" >云南产权交易中心</a></li>
						<li><a href="#" >云南市环保局</a></li>
						<li><a href="#" >云南市公安局</a></li>
						<li><a href="#" >云南市民政局</a></li>
						<li><a href="#" >云南市财政局</a></li>
						
						</ul>
		
	</div>

	</div>
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
	$(document).ready(function(){  
        var menuYloc = $(".ad").offset().top;  
        $(window).scroll(function (){  
            var offsetTop = menuYloc + $(window).scrollTop() +"px";  
            $(".ad").animate({top : offsetTop },{ duration:600 , queue:false });  
        });  
    });  
	$(document).ready(function(){
    $(document).off('click.bs.dropdown.data-api');
});
	</script>
	<script type="text/javascript">
<!--
  var speed=30//速度数值越大速度越慢
  document.getElementById("demozt2").innerHTML=document.getElementById("demozt1").innerHTML
  function Marquee(){
  if(document.getElementById("demozt2").offsetWidth-document.getElementById("demozt").scrollLeft<=0)
  document.getElementById("demozt").scrollLeft-=document.getElementById("demozt1").offsetWidth
  else{
  document.getElementById("demozt").scrollLeft++
  }
  }
  var MyMar=setInterval(Marquee,speed)
  document.getElementById("demozt").onmouseover=function() {clearInterval(MyMar)}
  document.getElementById("demozt").onmouseout=function() {MyMar=setInterval(Marquee,speed)}
      
-->
</script>
	
  </body>
</html>