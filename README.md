# 已知的bug

1. commit ID 为 dcf0036ccdb9c9ae0f6fe494f8efd3703ec6b9a2

   内容为：[更新到第14节课，view通知presenter生命周期变化](https://github.com/lsCoding666/MVVMDemo_sunofbeaches/commit/dcf0036ccdb9c9ae0f6fe494f8efd3703ec6b9a2)

   a. 代码会跑不起来，原因是MusicPresenter中没有实现接口

   ```
    override fun onStart() {
           TODO("Not yet implemented")
       }
   ```

   解决方法是删掉这些TODO

   b. 还有由于监听网络变化放在onCreate()，导致放到后台重新启动不会打log，解决方式是放到onStart

   c. 还有命名问题,应该是Lifecycle而不是LifeCircle

   第一个问题已经在第15节课代码修复,剩下两个在16节课代码中修复