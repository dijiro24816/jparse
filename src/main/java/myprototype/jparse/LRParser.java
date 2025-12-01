package myprototype.jparse;

import java.util.ArrayList;
import java.util.Stack;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.Symbol;
import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.nonterminal.ExpressionNode;

public class LRParser {
	// lookahead-set

	// goto table
	// action table
	public ArrayList<ArrayList<Integer>> table;

	public ArrayList<ArrayList<Integer>> getTable() {
		return table;
	}

	private void setTable(ArrayList<ArrayList<Integer>> table) {
		this.table = table;
	}

	public LRParser(GrammarScenario grammarScenario) {
		setTable(new ArrayList<ArrayList<Integer>>());
		apply(grammarScenario);
	}

	//	private Class<? extends Symbol> getNextDot()

	

	private void apply(GrammarScenario grammarScenario) {

	}

	public static void main(String[] args) {

		Production booleanLiteralTokenProduction = new Production(SymbolEnum.BOOLEAN_LITERAL_TOKEN);
		Production characterLiteralTokenProduction = new Production(SymbolEnum.CHARACTER_LITERAL_TOKEN);
		Production floatingPointLiteralTokenProduction = new Production(SymbolEnum.FLOATING_POINT_LITERAL_TOKEN);
		Production identifierTokenProduction = new Production(SymbolEnum.IDENTIFIER_TOKEN);
		Production integerLiteralTokenProduction = new Production(SymbolEnum.INTEGER_LITERAL_TOKEN);
		Production nullLiteralTokenProduction = new Production(SymbolEnum.NULL_LITERAL_TOKEN);
		Production stringLiteralTokenProduction = new Production(SymbolEnum.STRING_LITERAL_TOKEN);
		Production roundBracketOpenSeparatorTokenProduction = new Production(SymbolEnum.ROUND_BRACKET_OPEN_SEPARATOR_TOKEN);
		Production roundBracketCloseSeparatorTokenProduction = new Production(SymbolEnum.ROUND_BRACKET_CLOSE_SEPARATOR_TOKEN);
		Production curlyBracketOpenSeparatorTokenProduction = new Production(SymbolEnum.CURLY_BRACKET_OPEN_SEPARATOR_TOKEN);
		Production curlyBracketCloseSeparatorTokenProduction = new Production(SymbolEnum.CURLY_BRACKET_CLOSE_SEPARATOR_TOKEN);
		Production squareBracketOpenSeparatorTokenProduction = new Production(SymbolEnum.SQUARE_BRACKET_OPEN_SEPARATOR_TOKEN);
		Production squareBracketCloseSeparatorTokenProduction = new Production(SymbolEnum.SQUARE_BRACKET_CLOSE_SEPARATOR_TOKEN);
		Production semicolonSeparatorTokenProduction = new Production(SymbolEnum.SEMICOLON_SEPARATOR_TOKEN);
		Production commaSeparatorTokenProduction = new Production(SymbolEnum.COMMA_SEPARATOR_TOKEN);
		Production periodSeparatorTokenProduction = new Production(SymbolEnum.PERIOD_SEPARATOR_TOKEN);
		Production assignmentOperatorTokenProduction = new Production(SymbolEnum.ASSIGNMENT_OPERATOR_TOKEN);
		Production equalOperatorTokenProduction = new Production(SymbolEnum.EQUAL_OPERATOR_TOKEN);
		Production greaterThanOperatorTokenProduction = new Production(SymbolEnum.GREATER_THAN_OPERATOR_TOKEN);
		Production greaterThanEqualOperatorTokenProduction = new Production(SymbolEnum.GREATER_THAN_EQUAL_OPERATOR_TOKEN);
		Production rightShiftOperatorTokenProduction = new Production(SymbolEnum.RIGHT_SHIFT_OPERATOR_TOKEN);
		Production rightShiftAssignmentOperatorTokenProduction = new Production(SymbolEnum.RIGHT_SHIFT_ASSIGNMENT_OPERATOR_TOKEN);
		Production unsignedRightShiftOperatorTokenProduction = new Production(SymbolEnum.UNSIGNED_RIGHT_SHIFT_OPERATOR_TOKEN);
		Production lessThanOperatorTokenProduction = new Production(SymbolEnum.LESS_THAN_OPERATOR_TOKEN);
		Production lessThanEqualOperatorTokenProduction = new Production(SymbolEnum.LESS_THAN_EQUAL_OPERATOR_TOKEN);
		Production leftShiftOperatorTokenProduction = new Production(SymbolEnum.LEFT_SHIFT_OPERATOR_TOKEN);
		Production leftShiftAssignmentOperatorTokenProduction = new Production(SymbolEnum.LEFT_SHIFT_ASSIGNMENT_OPERATOR_TOKEN);
		Production logicalNotOperatorTokenProduction = new Production(SymbolEnum.LOGICAL_NOT_OPERATOR_TOKEN);
		Production notEqualOperatorTokenProduction = new Production(SymbolEnum.NOT_EQUAL_OPERATOR_TOKEN);
		Production bitwiseNotOperatorTokenProduction = new Production(SymbolEnum.BITWISE_NOT_OPERATOR_TOKEN);
		Production conditionalOperatorTokenProduction = new Production(SymbolEnum.CONDITIONAL_OPERATOR_TOKEN);
		Production colonOperatorTokenProduction = new Production(SymbolEnum.COLON_OPERATOR_TOKEN);
		Production bitwiseAndOperatorTokenProduction = new Production(SymbolEnum.BITWISE_AND_OPERATOR_TOKEN);
		Production bitwiseAndAssignmentOperatorTokenProduction = new Production(SymbolEnum.BITWISE_AND_ASSIGNMENT_OPERATOR_TOKEN);
		Production logicalAndOperatorTokenProduction = new Production(SymbolEnum.LOGICAL_AND_OPERATOR_TOKEN);
		Production bitwiseOrOperatorTokenProduction = new Production(SymbolEnum.BITWISE_OR_OPERATOR_TOKEN);
		Production bitwiseOrAssignmentOperatorTokenProduction = new Production(SymbolEnum.BITWISE_OR_ASSIGNMENT_OPERATOR_TOKEN);
		Production logicalOrOperatorTokenProduction = new Production(SymbolEnum.LOGICAL_OR_OPERATOR_TOKEN);
		Production additionOperatorTokenProduction = new Production(SymbolEnum.ADDITION_OPERATOR_TOKEN);
		Production additionAssignmentOperatorTokenProduction = new Production(SymbolEnum.ADDITION_ASSIGNMENT_OPERATOR_TOKEN);
		Production incrementOperatorTokenProduction = new Production(SymbolEnum.INCREMENT_OPERATOR_TOKEN);
		Production subtractionOperatorTokenProduction = new Production(SymbolEnum.SUBTRACTION_OPERATOR_TOKEN);
		Production subtractionAssignmentOperatorTokenProduction = new Production(SymbolEnum.SUBTRACTION_ASSIGNMENT_OPERATOR_TOKEN);
		Production decrementOperatorTokenProduction = new Production(SymbolEnum.DECREMENT_OPERATOR_TOKEN);
		Production multiplicationOperatorTokenProduction = new Production(SymbolEnum.MULTIPLICATION_OPERATOR_TOKEN);
		Production multiplicationAssignmentOperatorTokenProduction = new Production(SymbolEnum.MULTIPLICATION_ASSIGNMENT_OPERATOR_TOKEN);
		Production divisionOperatorTokenProduction = new Production(SymbolEnum.DIVISION_OPERATOR_TOKEN);
		Production divisionAssignmentOperatorTokenProduction = new Production(SymbolEnum.DIVISION_ASSIGNMENT_OPERATOR_TOKEN);
		Production bitwiseXorOperatorTokenProduction = new Production(SymbolEnum.BITWISE_XOR_OPERATOR_TOKEN);
		Production bitwiseXorAssignmentOperatorTokenProduction = new Production(SymbolEnum.BITWISE_XOR_ASSIGNMENT_OPERATOR_TOKEN);
		Production moduloOperatorTokenProduction = new Production(SymbolEnum.MODULO_OPERATOR_TOKEN);
		Production moduloAssignmentOperatorTokenProduction = new Production(SymbolEnum.MODULO_ASSIGNMENT_OPERATOR_TOKEN);
		Production abstractKeywordTokenProduction = new Production(SymbolEnum.ABSTRACT_KEYWORD_TOKEN);
		Production continueKeywordTokenProduction = new Production(SymbolEnum.CONTINUE_KEYWORD_TOKEN);
		Production forKeywordTokenProduction = new Production(SymbolEnum.FOR_KEYWORD_TOKEN);
		Production newKeywordTokenProduction = new Production(SymbolEnum.NEW_KEYWORD_TOKEN);
		Production switchKeywordTokenProduction = new Production(SymbolEnum.SWITCH_KEYWORD_TOKEN);
		Production assertKeywordTokenProduction = new Production(SymbolEnum.ASSERT_KEYWORD_TOKEN);
		Production defaultKeywordTokenProduction = new Production(SymbolEnum.DEFAULT_KEYWORD_TOKEN);
		Production ifKeywordTokenProduction = new Production(SymbolEnum.IF_KEYWORD_TOKEN);
		Production packageKeywordTokenProduction = new Production(SymbolEnum.PACKAGE_KEYWORD_TOKEN);
		Production synchronizedKeywordTokenProduction = new Production(SymbolEnum.SYNCHRONIZED_KEYWORD_TOKEN);
		Production booleanKeywordTokenProduction = new Production(SymbolEnum.BOOLEAN_KEYWORD_TOKEN);
		Production doKeywordTokenProduction = new Production(SymbolEnum.DO_KEYWORD_TOKEN);
		Production gotoKeywordTokenProduction = new Production(SymbolEnum.GOTO_KEYWORD_TOKEN);
		Production privateKeywordTokenProduction = new Production(SymbolEnum.PRIVATE_KEYWORD_TOKEN);
		Production thisKeywordTokenProduction = new Production(SymbolEnum.THIS_KEYWORD_TOKEN);
		Production breakKeywordTokenProduction = new Production(SymbolEnum.BREAK_KEYWORD_TOKEN);
		Production doubleKeywordTokenProduction = new Production(SymbolEnum.DOUBLE_KEYWORD_TOKEN);
		Production implementsKeywordTokenProduction = new Production(SymbolEnum.IMPLEMENTS_KEYWORD_TOKEN);
		Production protectedKeywordTokenProduction = new Production(SymbolEnum.PROTECTED_KEYWORD_TOKEN);
		Production throwKeywordTokenProduction = new Production(SymbolEnum.THROW_KEYWORD_TOKEN);
		Production byteKeywordTokenProduction = new Production(SymbolEnum.BYTE_KEYWORD_TOKEN);
		Production elseKeywordTokenProduction = new Production(SymbolEnum.ELSE_KEYWORD_TOKEN);
		Production importKeywordTokenProduction = new Production(SymbolEnum.IMPORT_KEYWORD_TOKEN);
		Production publicKeywordTokenProduction = new Production(SymbolEnum.PUBLIC_KEYWORD_TOKEN);
		Production throwsKeywordTokenProduction = new Production(SymbolEnum.THROWS_KEYWORD_TOKEN);
		Production caseKeywordTokenProduction = new Production(SymbolEnum.CASE_KEYWORD_TOKEN);
		Production enumKeywordTokenProduction = new Production(SymbolEnum.ENUM_KEYWORD_TOKEN);
		Production instanceofKeywordTokenProduction = new Production(SymbolEnum.INSTANCEOF_KEYWORD_TOKEN);
		Production returnKeywordTokenProduction = new Production(SymbolEnum.RETURN_KEYWORD_TOKEN);
		Production transientKeywordTokenProduction = new Production(SymbolEnum.TRANSIENT_KEYWORD_TOKEN);
		Production catchKeywordTokenProduction = new Production(SymbolEnum.CATCH_KEYWORD_TOKEN);
		Production extendsKeywordTokenProduction = new Production(SymbolEnum.EXTENDS_KEYWORD_TOKEN);
		Production intKeywordTokenProduction = new Production(SymbolEnum.INT_KEYWORD_TOKEN);
		Production shortKeywordTokenProduction = new Production(SymbolEnum.SHORT_KEYWORD_TOKEN);
		Production tryKeywordTokenProduction = new Production(SymbolEnum.TRY_KEYWORD_TOKEN);
		Production charKeywordTokenProduction = new Production(SymbolEnum.CHAR_KEYWORD_TOKEN);
		Production finalKeywordTokenProduction = new Production(SymbolEnum.FINAL_KEYWORD_TOKEN);
		Production interfaceKeywordTokenProduction = new Production(SymbolEnum.INTERFACE_KEYWORD_TOKEN);
		Production staticKeywordTokenProduction = new Production(SymbolEnum.STATIC_KEYWORD_TOKEN);
		Production voidKeywordTokenProduction = new Production(SymbolEnum.VOID_KEYWORD_TOKEN);
		Production classKeywordTokenProduction = new Production(SymbolEnum.CLASS_KEYWORD_TOKEN);
		Production finallyKeywordTokenProduction = new Production(SymbolEnum.FINALLY_KEYWORD_TOKEN);
		Production longKeywordTokenProduction = new Production(SymbolEnum.LONG_KEYWORD_TOKEN);
		Production strictfpKeywordTokenProduction = new Production(SymbolEnum.STRICTFP_KEYWORD_TOKEN);
		Production volatileKeywordTokenProduction = new Production(SymbolEnum.VOLATILE_KEYWORD_TOKEN);
		Production constKeywordTokenProduction = new Production(SymbolEnum.CONST_KEYWORD_TOKEN);
		Production floatKeywordTokenProduction = new Production(SymbolEnum.FLOAT_KEYWORD_TOKEN);
		Production nativeKeywordTokenProduction = new Production(SymbolEnum.NATIVE_KEYWORD_TOKEN);
		Production superKeywordTokenProduction = new Production(SymbolEnum.SUPER_KEYWORD_TOKEN);
		Production whileKeywordTokenProduction = new Production(SymbolEnum.WHILE_KEYWORD_TOKEN);
				
		Production expressionNodeProduction = new Production(
				SymbolEnum.EXPRESSION_NODE,
				new Rule((Stack<Symbol> stack) -> {
					return new ExpressionNode();
				}, additionOperatorTokenProduction, integerLiteralTokenProduction, integerLiteralTokenProduction));

	}
}
