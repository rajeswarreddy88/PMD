package net.sourceforge.pmd.lang.apex.rule.bestpractice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.pmd.lang.apex.ast.ASTAssignmentExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTBlockStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTExpressionStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTFieldDeclarationStatements;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTParameter;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclarationStatements;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.bestpractice.CustomApexNode;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.Node;

import net.sourceforge.pmd.lang.symboltable.NameOccurrence;
import net.sourceforge.pmd.lang.symboltable.Scope;


public  class UnUsedLocalVariableRule extends AbstractApexRule  {
	
	public UnUsedLocalVariableRule(){
		 addRuleChainVisit(ASTMethod.class);
	}
   @Override
   public Object visit(ASTVariableDeclarationStatements node, Object data) {
       List<ASTVariableExpression> usages = node.findDescendantsOfType(ASTVariableExpression.class);
       Set<String> referencedVariables = new HashSet<>();
       for (ASTVariableExpression variableExpression : usages) {
           referencedVariables.add(variableExpression.getNode().getIdentifier().getValue());
       }

      
       List<ASTMethodCallExpression> parameters = node.jjtGetParent().findDescendantsOfType(ASTMethodCallExpression.class);
      if(parameters.isEmpty()){
    	  addViolation(data, node);
      }
       
     /*  else
       {
    	   for (ASTAssignmentExpression parameter : parameters) {
    	   List<ASTVariableExpression> assignuasges = parameter.findDescendantsOfType(ASTVariableExpression.class);
           Set<String> assignedVariables = new HashSet<>();
           for (ASTVariableExpression variableExpression : assignuasges) {
        	   assignedVariables.add(variableExpression.getNode().getIdentifier().getValue());
        	    
           }
           if (!assignedVariables.containsAll(referencedVariables)) {
               addViolation(data, node);
           } 
           
           
       }
   }*/
       return data;
   }
	
	
	
// ASTVariableExpression node =  (ASTVariableExpression) decl.jjtGetChild(i).jjtGetChild(1).jjtGetChild(0);
}
/* List<ASTMethodCallExpression> methodExpression=node.jjtGetParent().findDescendantsOfType(ASTMethodCallExpression.class);
if(methodExpression.isEmpty()){
	   addViolation(data, node);
}*/