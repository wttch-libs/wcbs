# 模板库

方便下载后直接开启新的项目

## 1. Github Action

可以通过拷贝 .github-template 到 .github 来添加一些 github action

## 2. 快照版本

快照的发布可以不使用签名直接进行发布，具体版本号请在子模块中设置

## 3. 正式版本

正式版本请在本地发布，需要将签名证书放在根路径下，并在 gradle.properties 中配置相关账号密码和证书路径