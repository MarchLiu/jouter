# jouter

Jouter 项目实现一个通用的，基于字符串的子命令路由工具库，便于开发者构建包含复杂子命令的命令行程序。

这个项目是 [Task Router](https://github.com/fanfeilong/task_router) 项目的 scala 版本。

这个项目的起源是 CSDN AI 团队负责人，我的同事[范飞龙博士](https://github.com/fanfeilong)的一个
精彩的命令行设计。我们团队的工作中，这个基于路径`split.path.action`形式的命令路径的设计广泛的用在
了我们的各种项目中，发挥了很大的作用。

在日常工作中，我感觉这个设计可以进一步发展为一个强大友好的命令行程序设计范式，在现代的命令行工具开发时，
我们经常会遇到子命令/选项间有非正交的相关关系，用`.`分割，以层级关系划分成树状结构，符合程序员的经验，
辅以Shell的命令行完成提示（shell command completion），无需记忆完整的路径，也无需查阅文档，即可
按 tab 键查找完成子命令输入。

为了让这个精彩的设计能够进一步完善，长久的发展，我和飞龙经过讨论，决定构建它的开源版本，也就是 
[Task Router](https://github.com/fanfeilong/task_router) 项目。这个项目面向 Python 语言，
提供子命令路由功能，和 zsh、bash、fish 的自动完成支持。

而 jouter 项目，则是这个设计的 scala 实现，它专注于实现子命令路由，可以和命令行参数解释工具如
[opt parser](https://index.scala-lang.org/sellmerfud/optparse/optparse/2.2?target=_2.12)
或 [scopt](https://github.com/scopt/scopt) 组合使用。