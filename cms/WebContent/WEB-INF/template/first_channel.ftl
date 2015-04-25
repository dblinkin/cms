<html>
	<meta charset="utf-8"/>
	<body>
		
		<ul>
			<#list channels as ch1>
			<li href="${ch1.channelUrl?if_exists}">${ch1.channelName}</li>
				<#if ch1.children?size != 0>
					<ul>
					<#list ch1.children as ch2>
						<li href="${ch2.channelUrl?if_exists}">${ch2.channelName}</li>
					</#list>
					</ul>
				</#if>
			</#list>
		</ul>
	
		<span>当前一级栏目:<a href="${currentChannel.channelUrl?if_exists}">${currentChannel.channelName}</a></span>
		<#if currentChannel.children?size != 0>
			<ul>
			<#list currentChannel.children as ch2>
				<li href="${ch2.channelUrl?if_exists}">${ch2.channelName}</li>
			</#list>
			</ul>
		</#if>
		
	</body>
</html>