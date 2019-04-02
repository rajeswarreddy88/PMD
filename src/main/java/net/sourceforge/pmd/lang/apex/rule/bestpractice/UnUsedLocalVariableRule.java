
package net.sourceforge.pmd.lang.apex.rule.bestpractice;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTReferenceExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class UnUsedLocalVariableRule extends AbstractApexRule {

    public UnUsedLocalVariableRule() {
        addRuleChainVisit(ASTMethod.class);
    }

    @Override
    public Object visit(ASTMethod node, Object data) {
        // find all local variables
        List<ASTVariableDeclaration> localVariables = node.findDescendantsOfType(ASTVariableDeclaration.class);
        Map<String, ASTVariableDeclaration> localVariablesNodesByName = localVariables.stream()
                .collect(Collectors.toMap(ASTVariableDeclaration::getImage, Function.identity()));

        // find all usages (VariableExpressions and ReferenceExpressions)
        Set<String> usedVariables = new HashSet<>();
        List<ASTVariableExpression> usages = node.findDescendantsOfType(ASTVariableExpression.class);
        usedVariables.addAll(usages.stream().filter(n -> !(n.jjtGetParent() instanceof ASTVariableDeclaration))
                .filter(n -> !"".equals(n.getImage())).filter(n -> n.getImage() != null)
                .map(ASTVariableExpression::getImage).collect(Collectors.toSet()));
        List<ASTReferenceExpression> otherUsages = node.findDescendantsOfType(ASTReferenceExpression.class);
        usedVariables.addAll(otherUsages.stream().map(ASTReferenceExpression::getImage).collect(Collectors.toSet()));

        // figure out, which variables are unused
        Set<String> unusedVariables = new HashSet<>(localVariablesNodesByName.keySet());
        unusedVariables.removeAll(usedVariables);

        // report each unused variable
        for (String unusedVar : unusedVariables) {
            addViolation(data, localVariablesNodesByName.get(unusedVar), unusedVar);
        }
        return data;
    }
}
