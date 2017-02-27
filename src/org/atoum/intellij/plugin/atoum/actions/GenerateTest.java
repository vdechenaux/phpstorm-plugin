package org.atoum.intellij.plugin.atoum.actions;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.actions.CodeInsightAction;
import com.intellij.codeInsight.daemon.impl.quickfix.CreateClassKind;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.codeInsight.intention.impl.CreateClassDialog;
import com.intellij.ide.IdeView;
import com.intellij.ide.actions.CreateClassAction;
import com.intellij.ide.actions.CreateFileAction;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import com.intellij.psi.util.ClassKind;
import com.intellij.psi.util.PsiClassUtil;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.php.PhpIcons;
import com.jetbrains.php.actions.PhpNewBaseAction;
import com.jetbrains.php.actions.PhpNewClassAction;
import com.jetbrains.php.codeInsight.PhpScope;
import com.jetbrains.php.codeInsight.PhpScopeImpl;
import com.jetbrains.php.config.PhpLanguageLevel;
import com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocProperty;
import com.jetbrains.php.lang.psi.PhpFile;
import com.jetbrains.php.lang.psi.PhpFileImpl;
import com.jetbrains.php.lang.psi.PhpPsiElementFactory;
import com.jetbrains.php.lang.psi.PhpPsiUtil;
import com.jetbrains.php.lang.psi.elements.ClassReference;
import com.jetbrains.php.lang.psi.elements.Field;
import com.jetbrains.php.lang.psi.elements.Method;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.elements.impl.PhpClassImpl;
import com.jetbrains.php.lang.psi.elements.impl.PhpNamespaceImpl;
import com.jetbrains.php.roots.PhpNamespaceByFilesProvider;
import com.jetbrains.php.roots.PhpNamespaceByPsrProvider;
import org.atoum.intellij.plugin.atoum.Utils;
import org.atoum.intellij.plugin.atoum.model.RunnerConfiguration;
import org.atoum.intellij.plugin.atoum.run.Runner;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.util.Properties;

public class GenerateTest extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        FileTemplate fileTemplate = FileTemplateManager.getInstance(anActionEvent.getProject()).getInternalTemplate("PHPUnit Test.php");

        Properties properties = new Properties(FileTemplateManager.getInstance(anActionEvent.getProject()).getDefaultProperties());
        properties.setProperty("NAMESPACE", "bjr");
        properties.setProperty("TESTED_NAME", "lol");
        properties.setProperty("TESTED_NAMESPACE", "slt");

        String filenameWithoutExtension = "filename";
        PsiDirectory dir = PsiManager.getInstance(anActionEvent.getProject()).findDirectory(anActionEvent.getProject().getBaseDir());
        try {

            PsiElement element = FileTemplateUtil.createFromTemplate(fileTemplate, filenameWithoutExtension, properties, dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
