# 波波奇推荐系统简介

标签（空格分隔）： 波波奇

---

##1. 简介
推荐系统可视为主动搜索引擎，主动向用户推荐用户可能感兴趣的物品。在我们的实例中，我们使用推荐系统为用户推荐感兴趣的店铺。经验表明：早期的推荐系统适合基于人工规则推荐，后期的推荐系统适合基于推荐算法自动推荐。所以，目前我们的推荐系统为：人工规则为主，推荐算法为辅。
##2. 基于规则的推荐
###2.1 类目推荐类目 
根据店铺的类别推荐一个或多个特定类别，使用的数据表为```mp_rule_category_rec_list```:
```
CREATE TABLE `mp_rule_category_rec_list` (
  `CATEGORY_ID` int(11) DEFAULT NULL COMMENT '类目ID',
  `REC_CATEGORY_ID` int(11) DEFAULT NULL COMMENT '推荐的特定类目ID',
  `REC_LIST` varchar(5120) DEFAULT NULL COMMENT '特定类目的店铺列表',
  UNIQUE KEY `UNIQUE_ID` (`CATEGORY_ID`,`REC_CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
第三个字段```REC_LIST```为推荐列表，其中的数据等效于java中的Map，Key为店铺ID，Value为权重，并在键值之间使用ASCII字符\003分隔，键值对之间使用\002分隔。一个示例如下（实际中没有空格）：

```
shop1 \003 90  \002 shop2 \003 80 \002 shop3 \003 70
```
**注意**：第三个字段```REC_LIST```可为空，此时推荐列表为：该店铺所属商圈的特定类目下的所有店铺且权重均为100。

###2.2 商圈推荐特定店铺
在指定商圈推荐特定的店铺列表，使用的数据表为```mp_rule_shop_center_rec_list```:
```
CREATE TABLE `mp_rule_shop_center_rec_list` (
  `GROUP_ID` varchar(32) DEFAULT NULL COMMENT '商圈ID',
  `REC_LIST` varchar(5120) DEFAULT NULL COMMENT '推荐的店铺列表',
  UNIQUE KEY `GROUP_ID` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
字段```REC_LIST```同2.1中的介绍。
###2.3 店铺推荐特定店铺
在指定店铺推荐特点的店铺列表，使用的数据表为```mp_rule_shop_rec_list```:
```
CREATE TABLE `mp_rule_shop_rec_list` (
  `SHOP_CODE` varchar(32) DEFAULT NULL COMMENT '店铺ID',
  `REC_LIST` varchar(5120) DEFAULT NULL COMMENT '推荐的店铺列表',
  UNIQUE KEY `SHOP_CODE` (`SHOP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
字段```REC_LIST```同2.1中的介绍。

##3. 基于算法的推荐
###3.1 基于物品的协同过滤算法
根据用户支付时的店铺推荐相似的其他店铺。目前分为离线部分和在线部分，其中离线部分从数据表```mp_shop_meta```读取店铺信息（主要为店铺属性PROPERTIES）并计算店铺之间的相似度，将结果存入数据表```mp_shop_shop_rec_list```；在线部分从数据表```mp_shop_shop_rec_list```中读出推荐结果，并根据请求推送结果。两个数据表结构如下:
```
CREATE TABLE `mp_shop_meta` (
  `SHOP_CODE` varchar(32) DEFAULT NULL COMMENT '店铺ID',
  `GROUP_ID` varchar(32) DEFAULT NULL COMMENT '所属商圈ID',
  `CATEGORY` int(11) DEFAULT NULL COMMENT '店铺所属分类，分类ID',
  `KEYWORDS` varchar(255) DEFAULT NULL COMMENT '关键词',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述性文本',
  `PROPERTIES` varchar(5120) DEFAULT NULL COMMENT '属性-属性值kv串',
  `POSITION` varchar(100) DEFAULT NULL COMMENT '地址',
  `POS_TYPE` varchar(10) DEFAULT NULL COMMENT '地址类型',
  UNIQUE KEY `SHOP_CODE` (`SHOP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mp_shop_shop_rec_list` (
  `SHOP_CODE` varchar(32) DEFAULT NULL COMMENT '店铺ID',
  `REC_LIST` varchar(5120) DEFAULT NULL COMMENT '相似的topN店铺列表',
  `CONFIG_NAME` varchar(50) DEFAULT NULL COMMENT '保留',
  `CONFIG_VALUE` varchar(50) DEFAULT NULL COMMENT '保留',
  UNIQUE KEY `SHOP_CODE` (`SHOP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
字段```REC_LIST```同2.1中的介绍。

以下算法待完成
###3.2 基于用户的协同过滤算法
根据用户推荐相似用户去过的其他店铺。分为离线部分和在线部分，其中离线部分从数据表```mp_user_meta```读取用户标签信息并计算用户之间的相似度，从相似度最高的K个用户中选出去过的店铺数据，将结果存入数据表```mp_user_shop_rec_list```；在线部分从数据表```mp_user_shop_rec_list```中读出推荐结果，并根据请求推送结果。两个数据表结构如下:
```
CREATE TABLE `mp_user_meta` (
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `TAGS` varchar(5120) DEFAULT NULL COMMENT '标签-标签值kv串。不同tag之间用\002分隔，tag和value之间用\003分隔',
  UNIQUE KEY `USER_ID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mp_user_shop_rec_list` (
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `REC_LIST` varchar(5120) DEFAULT NULL COMMENT '用户感兴趣的topN店铺',
  `CONFIG_NAME` varchar(50) DEFAULT NULL COMMENT '保留',
  `CONFIG_VALUE` varchar(50) DEFAULT NULL COMMENT '保留',
  UNIQUE KEY `USER_ID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
字段```REC_LIST```同2.1中的介绍。

###3.3 基于特征的推荐算法
根据用户喜欢的某些特征推荐含有该特征的店铺。特征存储在数据表```mp_user_feature```中，某特征对应的店铺在数据表```mp_tag_shop_rec_list```中，数据表结构如下：
```
CREATE TABLE `mp_user_feature` (
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `FEATURE` varchar(5120) DEFAULT NULL COMMENT '用户特征，向量形式',
  `SIMHASH` varchar(32) DEFAULT NULL COMMENT 'feature的simHash值',
  `CONFIG_NAME` varchar(50) DEFAULT NULL COMMENT 'future_len:向量维度，density:向量存储形式，0稀疏格式，1平坦格式',
  `CONFIG_VALUE` varchar(50) DEFAULT NULL COMMENT '配置值',
  UNIQUE KEY `USER_ID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mp_tag_shop_rec_list` (
  `TAG_INDEX` varchar(32) DEFAULT NULL COMMENT '用户特征索引',
  `REC_LIST` varchar(5120) DEFAULT NULL COMMENT '该特征的用户感兴趣的topN店铺',
  `CONFIG_NAME` varchar(50) DEFAULT NULL COMMENT '保留',
  `CONFIG_VALUE` varchar(50) DEFAULT NULL COMMENT '保留',
  UNIQUE KEY `TAG_ID` (`TAG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

##4 总述
目前成熟的推荐算法还有很多，诸如：隐语义模型、随机森林决策、事件地点上下文相关等等。纵观这些算法，难点都在打标签的步骤，包括用户标签、商品标签以及店铺标签。目前这些标签并不能全部通过计算获得，有相当一部分需要人工定义。


