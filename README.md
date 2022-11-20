# 大学生选课管理系统

# 源码一些说明
- 基于Spring Boot框架实现快速开发
- jbbc版本用的比较旧是5.1.37的，如果连不上数据库，可能跟你的本机版本不一致，直接修改pom.xml的版本过去即可。

## 功能模块说明
### 教务处管理
#### 开课、开班审批，排课处理，班级操作，选课时间段管理
- 使用了sql解决了开课开班的时间段的冲突
### 教师端
#### 开课开班管理，班级学生信息查看，课表查询
### 学生端
#### 选课，退选，课表查询
- 使用了sql解决了选课的时间段的冲突

### 效果图
学生选课及选班
![学生选课及选班](https://github.com/yantao1995/PictureReference/blob/master/CourseSelectionSystem/%E5%AD%A6%E7%94%9F%E9%80%89%E8%AF%BE%E5%8F%8A%E9%80%89%E7%8F%AD.png)

学生课表
![学生课表](https://github.com/yantao1995/PictureReference/blob/master/CourseSelectionSystem/%E5%AD%A6%E7%94%9F%E8%AF%BE%E8%A1%A8%E8%87%AA%E5%8A%A8%E7%94%9F%E6%88%90.png)

开课开班审批（可选用周节次一键生成）
![开课开班审批](https://github.com/yantao1995/PictureReference/blob/master/CourseSelectionSystem/%E5%BC%80%E8%AF%BE%E5%BC%80%E7%8F%AD%E5%AE%A1%E6%89%B9.png)


##### [更多效果图](https://github.com/yantao1995/PictureReference/tree/master/CourseSelectionSystem)
