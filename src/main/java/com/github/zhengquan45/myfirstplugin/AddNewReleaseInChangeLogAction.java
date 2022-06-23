package com.github.zhengquan45.myfirstplugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;

import java.time.LocalDateTime;

/**
 *
 *
 * @author 郑权
 * @date 2022-06-22
 */
public class AddNewReleaseInChangeLogAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (null == editor) {
            return;
        }


        //下面这句话也可以使用 lambda 语法写：Runnable runnable = () -> editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), TransitionUtils.spacingText(selectedText));
        Runnable runnable = () -> {
            //先对空格、制表符进行换掉，然后再重新进行分隔处理。其中分行不处理，因为在写文章的时候复制的一些内容分行是有意义的。
            String text = editor.getDocument().getText();
            LocalDateTime localDateTime = LocalDateTime.now();
            String newText = text + "\n## [Unreleased] - "+localDateTime.toString()+"\n" +
                    "### Added\n" +
                    "- \"Why keep a changelog?\" section.\n" +
                    "- \"Who needs a changelog?\" section.\n" +
                    "- \"How do I make a changelog?\" section.\n"+"### Changed\n\n"+"### Removed\n\n";
            editor.getDocument().setText(newText);
        };

        WriteCommandAction.runWriteCommandAction(e.getData(PlatformDataKeys.PROJECT), runnable);
    }
}
