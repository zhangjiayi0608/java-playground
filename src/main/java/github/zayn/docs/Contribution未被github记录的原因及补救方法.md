###原因
- 进行commit的账号没有被关联到你的github中
- 不是版本库的默认分支进行的commit
- 仓库是fork的不是主仓库

###补救措施

针对commit账号不是github账号的补救措施，只能将所有提交用户名全部更改，重写整个git repo的历史

<big>警告： 这种行为对你的 repo 的历史具有破坏性。如果你的 repo 是与他人协同工作的，重写已发布的历史是一种不好的习惯。仅限紧急情况执行该操作</big>

####使用脚本改变repo的git历史

1. Mac、Linux下打开Terminal，Windows下打开命令提示符（command prompt）
2. 给你的repo创建一个全新的clone
   git clone --bare https://github.com/zhangjiayi0608/java-playground.git（该项目地址）
   cd java-playground.git
3. 复制粘贴脚本，并根据你的信息修改以下变量：旧的Email地址，正确的用户名，正确的邮件地址
```shell script
#!/bin/sh
git filter-branch --env-filter '
OLD_EMAIL="旧的Email地址"
CORRECT_NAME="正确的用户名"
CORRECT_EMAIL="正确的邮件地址"
if [ "$GIT_COMMITTER_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_COMMITTER_NAME="$CORRECT_NAME"
    export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
fi
if [ "$GIT_AUTHOR_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_AUTHOR_NAME="$CORRECT_NAME"
    export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
fi
' --tag-name-filter cat -- --branches --tags
```
4. 保存脚本并执行
5. git log 命令查看Git历史有没有错误
6. 正确的历史push到github
```shell script
git push --force --tags origin 'refs/heads/*'
```
7. 删除临时创建的clone
```shell script
cd ..
rm -rf repo.git
```