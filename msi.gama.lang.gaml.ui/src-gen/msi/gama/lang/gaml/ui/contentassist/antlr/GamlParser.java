/*
* generated by Xtext
*/
package msi.gama.lang.gaml.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import msi.gama.lang.gaml.services.GamlGrammarAccess;

public class GamlParser extends AbstractContentAssistParser {
	
	@Inject
	private GamlGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected msi.gama.lang.gaml.ui.contentassist.antlr.internal.InternalGamlParser createParser() {
		msi.gama.lang.gaml.ui.contentassist.antlr.internal.InternalGamlParser result = new msi.gama.lang.gaml.ui.contentassist.antlr.internal.InternalGamlParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getModelAccess().getAlternatives(), "rule__Model__Alternatives");
					put(grammarAccess.getBuiltInStatementKeyAccess().getAlternatives(), "rule__BuiltInStatementKey__Alternatives");
					put(grammarAccess.getStatementAccess().getAlternatives(), "rule__Statement__Alternatives");
					put(grammarAccess.getStatementAccess().getAlternatives_1(), "rule__Statement__Alternatives_1");
					put(grammarAccess.getIfStatementAccess().getElseAlternatives_4_1_0(), "rule__IfStatement__ElseAlternatives_4_1_0");
					put(grammarAccess.getClassicStatementAccess().getAlternatives_4(), "rule__ClassicStatement__Alternatives_4");
					put(grammarAccess.getDefinitionStatementAccess().getNameAlternatives_2_0(), "rule__DefinitionStatement__NameAlternatives_2_0");
					put(grammarAccess.getDefinitionStatementAccess().getAlternatives_3_1(), "rule__DefinitionStatement__Alternatives_3_1");
					put(grammarAccess.getDefinitionStatementAccess().getAlternatives_5(), "rule__DefinitionStatement__Alternatives_5");
					put(grammarAccess.getAssignmentStatementAccess().getKeyAlternatives_1_0(), "rule__AssignmentStatement__KeyAlternatives_1_0");
					put(grammarAccess.getArgumentDefinitionAccess().getNameAlternatives_2_0(), "rule__ArgumentDefinition__NameAlternatives_2_0");
					put(grammarAccess.getFacetAccess().getAlternatives(), "rule__Facet__Alternatives");
					put(grammarAccess.getDefinitionFacetKeyAccess().getAlternatives(), "rule__DefinitionFacetKey__Alternatives");
					put(grammarAccess.getClassicFacetAccess().getAlternatives(), "rule__ClassicFacet__Alternatives");
					put(grammarAccess.getClassicFacetAccess().getAlternatives_0_0_0(), "rule__ClassicFacet__Alternatives_0_0_0");
					put(grammarAccess.getClassicFacetAccess().getNameAlternatives_1_1_0(), "rule__ClassicFacet__NameAlternatives_1_1_0");
					put(grammarAccess.getFunctionFacetAccess().getAlternatives_0(), "rule__FunctionFacet__Alternatives_0");
					put(grammarAccess.getBlockAccess().getAlternatives_1(), "rule__Block__Alternatives_1");
					put(grammarAccess.getRelationalAccess().getOpAlternatives_1_0_1_0(), "rule__Relational__OpAlternatives_1_0_1_0");
					put(grammarAccess.getArgPairExprAccess().getAlternatives_1(), "rule__ArgPairExpr__Alternatives_1");
					put(grammarAccess.getArgPairExprAccess().getArgAlternatives_1_0_0_0(), "rule__ArgPairExpr__ArgAlternatives_1_0_0_0");
					put(grammarAccess.getPairExprAccess().getAlternatives(), "rule__PairExpr__Alternatives");
					put(grammarAccess.getAdditionAccess().getOpAlternatives_1_0_1_0(), "rule__Addition__OpAlternatives_1_0_1_0");
					put(grammarAccess.getMultiplicationAccess().getOpAlternatives_1_0_1_0(), "rule__Multiplication__OpAlternatives_1_0_1_0");
					put(grammarAccess.getGamlUnitExprAccess().getOpAlternatives_1_0_1_0(), "rule__GamlUnitExpr__OpAlternatives_1_0_1_0");
					put(grammarAccess.getGamlUnaryExprAccess().getAlternatives(), "rule__GamlUnaryExpr__Alternatives");
					put(grammarAccess.getGamlUnaryExprAccess().getAlternatives_1_1(), "rule__GamlUnaryExpr__Alternatives_1_1");
					put(grammarAccess.getGamlUnaryExprAccess().getOpAlternatives_1_1_1_0_0(), "rule__GamlUnaryExpr__OpAlternatives_1_1_1_0_0");
					put(grammarAccess.getPrimaryExpressionAccess().getAlternatives(), "rule__PrimaryExpression__Alternatives");
					put(grammarAccess.getAbstractRefAccess().getAlternatives(), "rule__AbstractRef__Alternatives");
					put(grammarAccess.getParameterAccess().getAlternatives_1(), "rule__Parameter__Alternatives_1");
					put(grammarAccess.getGamlVarRefAccess().getAlternatives(), "rule__GamlVarRef__Alternatives");
					put(grammarAccess.getTerminalExpressionAccess().getAlternatives(), "rule__TerminalExpression__Alternatives");
					put(grammarAccess.getModelAccess().getGroup_0(), "rule__Model__Group_0__0");
					put(grammarAccess.getModelAccess().getGroup_1(), "rule__Model__Group_1__0");
					put(grammarAccess.getImportAccess().getGroup(), "rule__Import__Group__0");
					put(grammarAccess.getEquationAccess().getGroup(), "rule__Equation__Group__0");
					put(grammarAccess.getIfStatementAccess().getGroup(), "rule__IfStatement__Group__0");
					put(grammarAccess.getIfStatementAccess().getGroup_4(), "rule__IfStatement__Group_4__0");
					put(grammarAccess.getClassicStatementAccess().getGroup(), "rule__ClassicStatement__Group__0");
					put(grammarAccess.getClassicStatementAccess().getGroup_1(), "rule__ClassicStatement__Group_1__0");
					put(grammarAccess.getDefinitionStatementAccess().getGroup(), "rule__DefinitionStatement__Group__0");
					put(grammarAccess.getDefinitionStatementAccess().getGroup_3(), "rule__DefinitionStatement__Group_3__0");
					put(grammarAccess.getContentsAccess().getGroup(), "rule__Contents__Group__0");
					put(grammarAccess.getContentsAccess().getGroup_2(), "rule__Contents__Group_2__0");
					put(grammarAccess.getReturnStatementAccess().getGroup(), "rule__ReturnStatement__Group__0");
					put(grammarAccess.getAssignmentStatementAccess().getGroup(), "rule__AssignmentStatement__Group__0");
					put(grammarAccess.getParametersAccess().getGroup(), "rule__Parameters__Group__0");
					put(grammarAccess.getActionArgumentsAccess().getGroup(), "rule__ActionArguments__Group__0");
					put(grammarAccess.getActionArgumentsAccess().getGroup_1(), "rule__ActionArguments__Group_1__0");
					put(grammarAccess.getArgumentDefinitionAccess().getGroup(), "rule__ArgumentDefinition__Group__0");
					put(grammarAccess.getArgumentDefinitionAccess().getGroup_3(), "rule__ArgumentDefinition__Group_3__0");
					put(grammarAccess.getClassicFacetAccess().getGroup_0(), "rule__ClassicFacet__Group_0__0");
					put(grammarAccess.getClassicFacetAccess().getGroup_0_0(), "rule__ClassicFacet__Group_0_0__0");
					put(grammarAccess.getClassicFacetAccess().getGroup_0_0_0_0(), "rule__ClassicFacet__Group_0_0_0_0__0");
					put(grammarAccess.getClassicFacetAccess().getGroup_1(), "rule__ClassicFacet__Group_1__0");
					put(grammarAccess.getFunctionFacetAccess().getGroup(), "rule__FunctionFacet__Group__0");
					put(grammarAccess.getBlockAccess().getGroup(), "rule__Block__Group__0");
					put(grammarAccess.getBlockAccess().getGroup_1_0(), "rule__Block__Group_1_0__0");
					put(grammarAccess.getBlockAccess().getGroup_1_0_0(), "rule__Block__Group_1_0_0__0");
					put(grammarAccess.getBlockAccess().getGroup_1_1(), "rule__Block__Group_1_1__0");
					put(grammarAccess.getTernExpAccess().getGroup(), "rule__TernExp__Group__0");
					put(grammarAccess.getTernExpAccess().getGroup_1(), "rule__TernExp__Group_1__0");
					put(grammarAccess.getOrExpAccess().getGroup(), "rule__OrExp__Group__0");
					put(grammarAccess.getOrExpAccess().getGroup_1(), "rule__OrExp__Group_1__0");
					put(grammarAccess.getAndExpAccess().getGroup(), "rule__AndExp__Group__0");
					put(grammarAccess.getAndExpAccess().getGroup_1(), "rule__AndExp__Group_1__0");
					put(grammarAccess.getRelationalAccess().getGroup(), "rule__Relational__Group__0");
					put(grammarAccess.getRelationalAccess().getGroup_1(), "rule__Relational__Group_1__0");
					put(grammarAccess.getRelationalAccess().getGroup_1_0(), "rule__Relational__Group_1_0__0");
					put(grammarAccess.getArgPairExprAccess().getGroup(), "rule__ArgPairExpr__Group__0");
					put(grammarAccess.getArgPairExprAccess().getGroup_1_0(), "rule__ArgPairExpr__Group_1_0__0");
					put(grammarAccess.getArgPairExprAccess().getGroup_1_1(), "rule__ArgPairExpr__Group_1_1__0");
					put(grammarAccess.getPairExprAccess().getGroup_1(), "rule__PairExpr__Group_1__0");
					put(grammarAccess.getPairExprAccess().getGroup_1_1(), "rule__PairExpr__Group_1_1__0");
					put(grammarAccess.getPairExprAccess().getGroup_1_1_0(), "rule__PairExpr__Group_1_1_0__0");
					put(grammarAccess.getAdditionAccess().getGroup(), "rule__Addition__Group__0");
					put(grammarAccess.getAdditionAccess().getGroup_1(), "rule__Addition__Group_1__0");
					put(grammarAccess.getAdditionAccess().getGroup_1_0(), "rule__Addition__Group_1_0__0");
					put(grammarAccess.getMultiplicationAccess().getGroup(), "rule__Multiplication__Group__0");
					put(grammarAccess.getMultiplicationAccess().getGroup_1(), "rule__Multiplication__Group_1__0");
					put(grammarAccess.getMultiplicationAccess().getGroup_1_0(), "rule__Multiplication__Group_1_0__0");
					put(grammarAccess.getGamlBinaryExprAccess().getGroup(), "rule__GamlBinaryExpr__Group__0");
					put(grammarAccess.getGamlBinaryExprAccess().getGroup_1(), "rule__GamlBinaryExpr__Group_1__0");
					put(grammarAccess.getGamlBinaryExprAccess().getGroup_1_0(), "rule__GamlBinaryExpr__Group_1_0__0");
					put(grammarAccess.getGamlUnitExprAccess().getGroup(), "rule__GamlUnitExpr__Group__0");
					put(grammarAccess.getGamlUnitExprAccess().getGroup_1(), "rule__GamlUnitExpr__Group_1__0");
					put(grammarAccess.getGamlUnitExprAccess().getGroup_1_0(), "rule__GamlUnitExpr__Group_1_0__0");
					put(grammarAccess.getGamlUnaryExprAccess().getGroup_1(), "rule__GamlUnaryExpr__Group_1__0");
					put(grammarAccess.getGamlUnaryExprAccess().getGroup_1_1_0(), "rule__GamlUnaryExpr__Group_1_1_0__0");
					put(grammarAccess.getGamlUnaryExprAccess().getGroup_1_1_0_1(), "rule__GamlUnaryExpr__Group_1_1_0_1__0");
					put(grammarAccess.getGamlUnaryExprAccess().getGroup_1_1_1(), "rule__GamlUnaryExpr__Group_1_1_1__0");
					put(grammarAccess.getAccessAccess().getGroup(), "rule__Access__Group__0");
					put(grammarAccess.getAccessAccess().getGroup_1(), "rule__Access__Group_1__0");
					put(grammarAccess.getAccessAccess().getGroup_1_0(), "rule__Access__Group_1_0__0");
					put(grammarAccess.getMemberRefAccess().getGroup(), "rule__MemberRef__Group__0");
					put(grammarAccess.getMemberRefAccess().getGroup_1(), "rule__MemberRef__Group_1__0");
					put(grammarAccess.getMemberRefAccess().getGroup_1_1(), "rule__MemberRef__Group_1_1__0");
					put(grammarAccess.getPrimaryExpressionAccess().getGroup_2(), "rule__PrimaryExpression__Group_2__0");
					put(grammarAccess.getPrimaryExpressionAccess().getGroup_3(), "rule__PrimaryExpression__Group_3__0");
					put(grammarAccess.getPrimaryExpressionAccess().getGroup_4(), "rule__PrimaryExpression__Group_4__0");
					put(grammarAccess.getPrimaryExpressionAccess().getGroup_5(), "rule__PrimaryExpression__Group_5__0");
					put(grammarAccess.getPrimaryExpressionAccess().getGroup_5_5(), "rule__PrimaryExpression__Group_5_5__0");
					put(grammarAccess.getFunctionAccess().getGroup(), "rule__Function__Group__0");
					put(grammarAccess.getFunctionAccess().getGroup_1(), "rule__Function__Group_1__0");
					put(grammarAccess.getParameterAccess().getGroup(), "rule__Parameter__Group__0");
					put(grammarAccess.getParameterAccess().getGroup_1_1(), "rule__Parameter__Group_1_1__0");
					put(grammarAccess.getExpressionListAccess().getGroup(), "rule__ExpressionList__Group__0");
					put(grammarAccess.getExpressionListAccess().getGroup_1(), "rule__ExpressionList__Group_1__0");
					put(grammarAccess.getParameterListAccess().getGroup(), "rule__ParameterList__Group__0");
					put(grammarAccess.getParameterListAccess().getGroup_1(), "rule__ParameterList__Group_1__0");
					put(grammarAccess.getUnitNameAccess().getGroup(), "rule__UnitName__Group__0");
					put(grammarAccess.getVariableRefAccess().getGroup(), "rule__VariableRef__Group__0");
					put(grammarAccess.getTerminalExpressionAccess().getGroup_0(), "rule__TerminalExpression__Group_0__0");
					put(grammarAccess.getTerminalExpressionAccess().getGroup_1(), "rule__TerminalExpression__Group_1__0");
					put(grammarAccess.getTerminalExpressionAccess().getGroup_2(), "rule__TerminalExpression__Group_2__0");
					put(grammarAccess.getTerminalExpressionAccess().getGroup_3(), "rule__TerminalExpression__Group_3__0");
					put(grammarAccess.getTerminalExpressionAccess().getGroup_4(), "rule__TerminalExpression__Group_4__0");
					put(grammarAccess.getModelAccess().getNameAssignment_0_1(), "rule__Model__NameAssignment_0_1");
					put(grammarAccess.getModelAccess().getImportsAssignment_0_2(), "rule__Model__ImportsAssignment_0_2");
					put(grammarAccess.getModelAccess().getStatementsAssignment_0_3(), "rule__Model__StatementsAssignment_0_3");
					put(grammarAccess.getModelAccess().getNameAssignment_1_1(), "rule__Model__NameAssignment_1_1");
					put(grammarAccess.getModelAccess().getExprAssignment_1_3(), "rule__Model__ExprAssignment_1_3");
					put(grammarAccess.getImportAccess().getImportURIAssignment_1(), "rule__Import__ImportURIAssignment_1");
					put(grammarAccess.getEquationAccess().getFunctionAssignment_0(), "rule__Equation__FunctionAssignment_0");
					put(grammarAccess.getEquationAccess().getKeyAssignment_1(), "rule__Equation__KeyAssignment_1");
					put(grammarAccess.getEquationAccess().getExprAssignment_2(), "rule__Equation__ExprAssignment_2");
					put(grammarAccess.getIfStatementAccess().getKeyAssignment_0(), "rule__IfStatement__KeyAssignment_0");
					put(grammarAccess.getIfStatementAccess().getExprAssignment_2(), "rule__IfStatement__ExprAssignment_2");
					put(grammarAccess.getIfStatementAccess().getBlockAssignment_3(), "rule__IfStatement__BlockAssignment_3");
					put(grammarAccess.getIfStatementAccess().getElseAssignment_4_1(), "rule__IfStatement__ElseAssignment_4_1");
					put(grammarAccess.getClassicStatementAccess().getKeyAssignment_0(), "rule__ClassicStatement__KeyAssignment_0");
					put(grammarAccess.getClassicStatementAccess().getExprAssignment_2(), "rule__ClassicStatement__ExprAssignment_2");
					put(grammarAccess.getClassicStatementAccess().getFacetsAssignment_3(), "rule__ClassicStatement__FacetsAssignment_3");
					put(grammarAccess.getClassicStatementAccess().getBlockAssignment_4_0(), "rule__ClassicStatement__BlockAssignment_4_0");
					put(grammarAccess.getDefinitionStatementAccess().getKeyAssignment_0(), "rule__DefinitionStatement__KeyAssignment_0");
					put(grammarAccess.getDefinitionStatementAccess().getOfAssignment_1(), "rule__DefinitionStatement__OfAssignment_1");
					put(grammarAccess.getDefinitionStatementAccess().getNameAssignment_2(), "rule__DefinitionStatement__NameAssignment_2");
					put(grammarAccess.getDefinitionStatementAccess().getArgsAssignment_3_1_0(), "rule__DefinitionStatement__ArgsAssignment_3_1_0");
					put(grammarAccess.getDefinitionStatementAccess().getParamsAssignment_3_1_1(), "rule__DefinitionStatement__ParamsAssignment_3_1_1");
					put(grammarAccess.getDefinitionStatementAccess().getFacetsAssignment_4(), "rule__DefinitionStatement__FacetsAssignment_4");
					put(grammarAccess.getDefinitionStatementAccess().getBlockAssignment_5_0(), "rule__DefinitionStatement__BlockAssignment_5_0");
					put(grammarAccess.getContentsAccess().getTypeAssignment_1(), "rule__Contents__TypeAssignment_1");
					put(grammarAccess.getContentsAccess().getType2Assignment_2_1(), "rule__Contents__Type2Assignment_2_1");
					put(grammarAccess.getReturnStatementAccess().getKeyAssignment_0(), "rule__ReturnStatement__KeyAssignment_0");
					put(grammarAccess.getReturnStatementAccess().getExprAssignment_1(), "rule__ReturnStatement__ExprAssignment_1");
					put(grammarAccess.getAssignmentStatementAccess().getExprAssignment_0(), "rule__AssignmentStatement__ExprAssignment_0");
					put(grammarAccess.getAssignmentStatementAccess().getKeyAssignment_1(), "rule__AssignmentStatement__KeyAssignment_1");
					put(grammarAccess.getAssignmentStatementAccess().getValueAssignment_2(), "rule__AssignmentStatement__ValueAssignment_2");
					put(grammarAccess.getAssignmentStatementAccess().getFacetsAssignment_3(), "rule__AssignmentStatement__FacetsAssignment_3");
					put(grammarAccess.getParametersAccess().getParamsAssignment_1(), "rule__Parameters__ParamsAssignment_1");
					put(grammarAccess.getActionArgumentsAccess().getArgsAssignment_0(), "rule__ActionArguments__ArgsAssignment_0");
					put(grammarAccess.getActionArgumentsAccess().getArgsAssignment_1_1(), "rule__ActionArguments__ArgsAssignment_1_1");
					put(grammarAccess.getArgumentDefinitionAccess().getTypeAssignment_0(), "rule__ArgumentDefinition__TypeAssignment_0");
					put(grammarAccess.getArgumentDefinitionAccess().getOfAssignment_1(), "rule__ArgumentDefinition__OfAssignment_1");
					put(grammarAccess.getArgumentDefinitionAccess().getNameAssignment_2(), "rule__ArgumentDefinition__NameAssignment_2");
					put(grammarAccess.getArgumentDefinitionAccess().getDefaultAssignment_3_1(), "rule__ArgumentDefinition__DefaultAssignment_3_1");
					put(grammarAccess.getClassicFacetAccess().getKeyAssignment_0_0_0_0_0(), "rule__ClassicFacet__KeyAssignment_0_0_0_0_0");
					put(grammarAccess.getClassicFacetAccess().getKeyAssignment_0_0_0_1(), "rule__ClassicFacet__KeyAssignment_0_0_0_1");
					put(grammarAccess.getClassicFacetAccess().getExprAssignment_0_0_1(), "rule__ClassicFacet__ExprAssignment_0_0_1");
					put(grammarAccess.getClassicFacetAccess().getKeyAssignment_1_0(), "rule__ClassicFacet__KeyAssignment_1_0");
					put(grammarAccess.getClassicFacetAccess().getNameAssignment_1_1(), "rule__ClassicFacet__NameAssignment_1_1");
					put(grammarAccess.getFunctionFacetAccess().getKeyAssignment_0_0(), "rule__FunctionFacet__KeyAssignment_0_0");
					put(grammarAccess.getFunctionFacetAccess().getKeyAssignment_0_1(), "rule__FunctionFacet__KeyAssignment_0_1");
					put(grammarAccess.getFunctionFacetAccess().getExprAssignment_2(), "rule__FunctionFacet__ExprAssignment_2");
					put(grammarAccess.getBlockAccess().getFunctionAssignment_1_0_0_1(), "rule__Block__FunctionAssignment_1_0_0_1");
					put(grammarAccess.getBlockAccess().getStatementsAssignment_1_1_1(), "rule__Block__StatementsAssignment_1_1_1");
					put(grammarAccess.getTernExpAccess().getOpAssignment_1_1(), "rule__TernExp__OpAssignment_1_1");
					put(grammarAccess.getTernExpAccess().getRightAssignment_1_2(), "rule__TernExp__RightAssignment_1_2");
					put(grammarAccess.getTernExpAccess().getIfFalseAssignment_1_4(), "rule__TernExp__IfFalseAssignment_1_4");
					put(grammarAccess.getOrExpAccess().getOpAssignment_1_1(), "rule__OrExp__OpAssignment_1_1");
					put(grammarAccess.getOrExpAccess().getRightAssignment_1_2(), "rule__OrExp__RightAssignment_1_2");
					put(grammarAccess.getAndExpAccess().getOpAssignment_1_1(), "rule__AndExp__OpAssignment_1_1");
					put(grammarAccess.getAndExpAccess().getRightAssignment_1_2(), "rule__AndExp__RightAssignment_1_2");
					put(grammarAccess.getRelationalAccess().getOpAssignment_1_0_1(), "rule__Relational__OpAssignment_1_0_1");
					put(grammarAccess.getRelationalAccess().getRightAssignment_1_1(), "rule__Relational__RightAssignment_1_1");
					put(grammarAccess.getArgPairExprAccess().getArgAssignment_1_0_0(), "rule__ArgPairExpr__ArgAssignment_1_0_0");
					put(grammarAccess.getArgPairExprAccess().getOpAssignment_1_0_1(), "rule__ArgPairExpr__OpAssignment_1_0_1");
					put(grammarAccess.getArgPairExprAccess().getArgAssignment_1_1_0(), "rule__ArgPairExpr__ArgAssignment_1_1_0");
					put(grammarAccess.getArgPairExprAccess().getOpAssignment_1_1_1(), "rule__ArgPairExpr__OpAssignment_1_1_1");
					put(grammarAccess.getArgPairExprAccess().getRightAssignment_2(), "rule__ArgPairExpr__RightAssignment_2");
					put(grammarAccess.getPairExprAccess().getOpAssignment_1_1_0_1(), "rule__PairExpr__OpAssignment_1_1_0_1");
					put(grammarAccess.getPairExprAccess().getRightAssignment_1_1_1(), "rule__PairExpr__RightAssignment_1_1_1");
					put(grammarAccess.getAdditionAccess().getOpAssignment_1_0_1(), "rule__Addition__OpAssignment_1_0_1");
					put(grammarAccess.getAdditionAccess().getRightAssignment_1_1(), "rule__Addition__RightAssignment_1_1");
					put(grammarAccess.getMultiplicationAccess().getOpAssignment_1_0_1(), "rule__Multiplication__OpAssignment_1_0_1");
					put(grammarAccess.getMultiplicationAccess().getRightAssignment_1_1(), "rule__Multiplication__RightAssignment_1_1");
					put(grammarAccess.getGamlBinaryExprAccess().getOpAssignment_1_0_1(), "rule__GamlBinaryExpr__OpAssignment_1_0_1");
					put(grammarAccess.getGamlBinaryExprAccess().getRightAssignment_1_1(), "rule__GamlBinaryExpr__RightAssignment_1_1");
					put(grammarAccess.getGamlUnitExprAccess().getOpAssignment_1_0_1(), "rule__GamlUnitExpr__OpAssignment_1_0_1");
					put(grammarAccess.getGamlUnitExprAccess().getRightAssignment_1_1(), "rule__GamlUnitExpr__RightAssignment_1_1");
					put(grammarAccess.getGamlUnaryExprAccess().getOpAssignment_1_1_0_1_0(), "rule__GamlUnaryExpr__OpAssignment_1_1_0_1_0");
					put(grammarAccess.getGamlUnaryExprAccess().getRightAssignment_1_1_0_1_1(), "rule__GamlUnaryExpr__RightAssignment_1_1_0_1_1");
					put(grammarAccess.getGamlUnaryExprAccess().getOpAssignment_1_1_1_0(), "rule__GamlUnaryExpr__OpAssignment_1_1_1_0");
					put(grammarAccess.getGamlUnaryExprAccess().getRightAssignment_1_1_1_1(), "rule__GamlUnaryExpr__RightAssignment_1_1_1_1");
					put(grammarAccess.getAccessAccess().getArgsAssignment_1_1(), "rule__Access__ArgsAssignment_1_1");
					put(grammarAccess.getMemberRefAccess().getOpAssignment_1_1_0(), "rule__MemberRef__OpAssignment_1_1_0");
					put(grammarAccess.getMemberRefAccess().getRightAssignment_1_1_1(), "rule__MemberRef__RightAssignment_1_1_1");
					put(grammarAccess.getPrimaryExpressionAccess().getParamsAssignment_3_2(), "rule__PrimaryExpression__ParamsAssignment_3_2");
					put(grammarAccess.getPrimaryExpressionAccess().getExprsAssignment_4_2(), "rule__PrimaryExpression__ExprsAssignment_4_2");
					put(grammarAccess.getPrimaryExpressionAccess().getLeftAssignment_5_2(), "rule__PrimaryExpression__LeftAssignment_5_2");
					put(grammarAccess.getPrimaryExpressionAccess().getOpAssignment_5_3(), "rule__PrimaryExpression__OpAssignment_5_3");
					put(grammarAccess.getPrimaryExpressionAccess().getRightAssignment_5_4(), "rule__PrimaryExpression__RightAssignment_5_4");
					put(grammarAccess.getPrimaryExpressionAccess().getZAssignment_5_5_1(), "rule__PrimaryExpression__ZAssignment_5_5_1");
					put(grammarAccess.getFunctionAccess().getOpAssignment_1_0(), "rule__Function__OpAssignment_1_0");
					put(grammarAccess.getFunctionAccess().getArgsAssignment_1_2(), "rule__Function__ArgsAssignment_1_2");
					put(grammarAccess.getParameterAccess().getBuiltInFacetKeyAssignment_1_0(), "rule__Parameter__BuiltInFacetKeyAssignment_1_0");
					put(grammarAccess.getParameterAccess().getLeftAssignment_1_1_0(), "rule__Parameter__LeftAssignment_1_1_0");
					put(grammarAccess.getParameterAccess().getRightAssignment_2(), "rule__Parameter__RightAssignment_2");
					put(grammarAccess.getExpressionListAccess().getExprsAssignment_0(), "rule__ExpressionList__ExprsAssignment_0");
					put(grammarAccess.getExpressionListAccess().getExprsAssignment_1_1(), "rule__ExpressionList__ExprsAssignment_1_1");
					put(grammarAccess.getParameterListAccess().getExprsAssignment_0(), "rule__ParameterList__ExprsAssignment_0");
					put(grammarAccess.getParameterListAccess().getExprsAssignment_1_1(), "rule__ParameterList__ExprsAssignment_1_1");
					put(grammarAccess.getUnitNameAccess().getOpAssignment_1(), "rule__UnitName__OpAssignment_1");
					put(grammarAccess.getVariableRefAccess().getRefAssignment_1(), "rule__VariableRef__RefAssignment_1");
					put(grammarAccess.getTerminalExpressionAccess().getValueAssignment_0_1(), "rule__TerminalExpression__ValueAssignment_0_1");
					put(grammarAccess.getTerminalExpressionAccess().getValueAssignment_1_1(), "rule__TerminalExpression__ValueAssignment_1_1");
					put(grammarAccess.getTerminalExpressionAccess().getValueAssignment_2_1(), "rule__TerminalExpression__ValueAssignment_2_1");
					put(grammarAccess.getTerminalExpressionAccess().getValueAssignment_3_1(), "rule__TerminalExpression__ValueAssignment_3_1");
					put(grammarAccess.getTerminalExpressionAccess().getValueAssignment_4_1(), "rule__TerminalExpression__ValueAssignment_4_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			msi.gama.lang.gaml.ui.contentassist.antlr.internal.InternalGamlParser typedParser = (msi.gama.lang.gaml.ui.contentassist.antlr.internal.InternalGamlParser) parser;
			typedParser.entryRuleModel();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public GamlGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(GamlGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
