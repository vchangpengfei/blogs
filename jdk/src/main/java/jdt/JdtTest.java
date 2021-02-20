//package jdt;
//
//import org.apache.commons.io.FileUtils;
//import org.eclipse.jdt.core.JavaCore;
//import org.eclipse.jdt.core.dom.AST;
//import org.eclipse.jdt.core.dom.ASTParser;
//import org.eclipse.jdt.core.dom.CompilationUnit;
//
//import java.io.File;
//import java.util.Map;
//
//import static org.eclipse.jdt.core.dom.AST.JLS8;
//
//public class JdtTest {
//
//    public static void main(String[] args) {
//        ASTParser parser = ASTParser.newParser(AST.JLS8); //设置Java语言规范版本
//        parser.setKind(ASTParser.K_COMPILATION_UNIT);
//
//        parser.setCompilerOptions(null);
//        parser.setResolveBindings(true);
//
//        Map<String, String> compilerOptions = JavaCore.getOptions();
//        compilerOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8); //设置Java语言版本
//        compilerOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
//        compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
//        parser.setCompilerOptions(compilerOptions); //设置编译选项
//
//        String src = null;
//        try {
//            src = FileUtils.readFileToString(new File("JdtTest.java"),"UTF-8");  //要解析的文件
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        parser.setSource(src.toCharArray());
//        CompilationUnit cu = (CompilationUnit) parser.createAST(null);  //下个断点可以看看cu的types成员就是整个语法树
//        System.out.println(cu);
//    }
//}