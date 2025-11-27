package myprototype.jparse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.Symbol;
import myprototype.jparse.symbol.nonterminal.ExpressionNode;
import myprototype.jparse.symbol.nonterminal.Nonterminal;
import myprototype.jparse.symbol.terminal.BooleanLiteralToken;
import myprototype.jparse.symbol.terminal.CharacterLiteralToken;
import myprototype.jparse.symbol.terminal.FloatingPointLiteralToken;
import myprototype.jparse.symbol.terminal.IdentifierToken;
import myprototype.jparse.symbol.terminal.IntegerLiteralToken;
import myprototype.jparse.symbol.terminal.NullLiteralToken;
import myprototype.jparse.symbol.terminal.StringLiteralToken;
import myprototype.jparse.symbol.terminal.keyword.AbstractKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.AssertKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.BooleanKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.BreakKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ByteKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.CaseKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.CatchKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.CharKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ClassKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ConstKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ContinueKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.DefaultKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.DoKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.DoubleKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ElseKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.EnumKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ExtendsKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.FinalKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.FinallyKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.FloatKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ForKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.GotoKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.IfKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ImplementsKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ImportKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.InstanceofKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.IntKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.InterfaceKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.LongKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.NativeKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.NewKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.PackageKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.PrivateKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ProtectedKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.PublicKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ReturnKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ShortKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.StaticKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.StrictfpKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.SuperKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.SwitchKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.SynchronizedKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ThisKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ThrowKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ThrowsKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.TransientKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.TryKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.VoidKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.VolatileKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.WhileKeywordToken;
import myprototype.jparse.symbol.terminal.operator.AdditionAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.AdditionOperatorToken;
import myprototype.jparse.symbol.terminal.operator.AssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseAndAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseAndOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseNotOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseOrAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseOrOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseXorAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseXorOperatorToken;
import myprototype.jparse.symbol.terminal.operator.ColonOperatorToken;
import myprototype.jparse.symbol.terminal.operator.ConditionalOperatorToken;
import myprototype.jparse.symbol.terminal.operator.DecrementOperatorToken;
import myprototype.jparse.symbol.terminal.operator.DivisionAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.DivisionOperatorToken;
import myprototype.jparse.symbol.terminal.operator.EqualOperatorToken;
import myprototype.jparse.symbol.terminal.operator.GreaterThanEqualOperatorToken;
import myprototype.jparse.symbol.terminal.operator.GreaterThanOperatorToken;
import myprototype.jparse.symbol.terminal.operator.IncrementOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LeftShiftAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LeftShiftOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LessThanEqualOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LessThanOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LogicalAndOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LogicalNotOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LogicalOrOperatorToken;
import myprototype.jparse.symbol.terminal.operator.ModuloAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.ModuloOperatorToken;
import myprototype.jparse.symbol.terminal.operator.MultiplicationAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.MultiplicationOperatorToken;
import myprototype.jparse.symbol.terminal.operator.NotEqualOperatorToken;
import myprototype.jparse.symbol.terminal.operator.RightShiftAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.RightShiftOperatorToken;
import myprototype.jparse.symbol.terminal.operator.SubtractionAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.SubtractionOperatorToken;
import myprototype.jparse.symbol.terminal.operator.UnsignedRightShiftOperatorToken;
import myprototype.jparse.symbol.terminal.separator.CommaSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.CurlyBracketCloseSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.CurlyBracketOpenSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.PeriodSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.RoundBracketCloseSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.RoundBracketOpenSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.SemicolonSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.SquareBracketCloseSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.SquareBracketOpenSeparatorToken;

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

	private void completeCurrentDotsOnTerminal(RuleScenario ruleScenario) {

	}

	private void completeDots(List<RuleScenario> ruleScenarios) {
		ruleScenarios = ruleScenarios.stream()
				.sorted(
						Comparator.comparing(
								(ruleScenario) -> ((RuleScenario) ruleScenario).getDotProductionSymbol().getName()))
				.toList();

		int begIndex, endIndex;
		for (begIndex = 0, endIndex = 1; endIndex < ruleScenarios.size(); endIndex++) {
			if (ruleScenarios.get(begIndex).getDotProductionSymbol() != ruleScenarios.get(endIndex)
					.getDotProductionSymbol()) {
				List<RuleScenario> derivativeRuleScenarios = ruleScenarios.subList(begIndex, endIndex);
				if (ruleScenarios.get(begIndex).getDotProductionSymbol().isAssignableFrom(Nonterminal.class)) {
					// TODO: do for nonterminal
//					completeDots(ruleScenarios.get(begIndex).getDotProduction().getRules());
				} else {

				}

				begIndex = endIndex;
			}
		}
		// TODO: move dot to next
		completeDots(ruleScenarios.subList(begIndex, endIndex));
	}

	private void apply(GrammarScenario grammarScenario) {

	}

	public static void main(String[] args) {

		Production booleanLiteralTokenProduction = new Production(BooleanLiteralToken.class);
		Production characterLiteralTokenProduction = new Production(
				CharacterLiteralToken.class);
		Production floatingPointLiteralTokenProduction = new Production(
				FloatingPointLiteralToken.class);
		Production identifierTokenProduction = new Production(IdentifierToken.class);
		Production integerLiteralTokenProduction = new Production(IntegerLiteralToken.class);
		Production nullLiteralTokenProduction = new Production(NullLiteralToken.class);
		Production stringLiteralTokenProduction = new Production(StringLiteralToken.class);

		// Separator Token
		Production roundBracketOpenSeparatorTokenProduction = new Production(
				RoundBracketOpenSeparatorToken.class);
		Production roundBracketCloseSeparatorTokenProduction = new Production(
				RoundBracketCloseSeparatorToken.class);
		Production curlyBracketOpenSeparatorTokenProduction = new Production(
				CurlyBracketOpenSeparatorToken.class);
		Production curlyBracketCloseSeparatorTokenProduction = new Production(
				CurlyBracketCloseSeparatorToken.class);
		Production squareBracketOpenSeparatorTokenProduction = new Production(
				SquareBracketOpenSeparatorToken.class);
		Production squareBracketCloseSeparatorTokenProduction = new Production(
				SquareBracketCloseSeparatorToken.class);
		Production semicolonSeparatorTokenProduction = new Production(
				SemicolonSeparatorToken.class);
		Production commaSeparatorTokenProduction = new Production(CommaSeparatorToken.class);
		Production periodSeparatorTokenProduction = new Production(PeriodSeparatorToken.class);

		// Operator Token
		Production assignmentOperatorTokenProduction = new Production(
				AssignmentOperatorToken.class);
		Production equalOperatorTokenProduction = new Production(EqualOperatorToken.class);
		Production greaterThanOperatorTokenProduction = new Production(
				GreaterThanOperatorToken.class);
		Production greaterThanEqualOperatorTokenProduction = new Production(
				GreaterThanEqualOperatorToken.class);
		Production rightShiftOperatorTokenProduction = new Production(
				RightShiftOperatorToken.class);
		Production rightShiftAssignmentOperatorTokenProduction = new Production(
				RightShiftAssignmentOperatorToken.class);
		Production unsignedRightShiftOperatorTokenProduction = new Production(
				UnsignedRightShiftOperatorToken.class);
		Production lessThanOperatorTokenProduction = new Production(
				LessThanOperatorToken.class);
		Production lessThanEqualOperatorTokenProduction = new Production(
				LessThanEqualOperatorToken.class);
		Production leftShiftOperatorTokenProduction = new Production(
				LeftShiftOperatorToken.class);
		Production leftShiftAssignmentOperatorTokenProduction = new Production(
				LeftShiftAssignmentOperatorToken.class);
		Production logicalNotOperatorTokenProduction = new Production(
				LogicalNotOperatorToken.class);
		Production notEqualOperatorTokenProduction = new Production(
				NotEqualOperatorToken.class);
		Production bitwiseNotOperatorTokenProduction = new Production(
				BitwiseNotOperatorToken.class);
		Production conditionalOperatorTokenProduction = new Production(
				ConditionalOperatorToken.class);
		Production colonOperatorTokenProduction = new Production(ColonOperatorToken.class);
		Production bitwiseAndOperatorTokenProduction = new Production(
				BitwiseAndOperatorToken.class);
		Production bitwiseAndAssignmentOperatorTokenProduction = new Production(
				BitwiseAndAssignmentOperatorToken.class);
		Production logicalAndOperatorTokenProduction = new Production(
				LogicalAndOperatorToken.class);
		Production bitwiseOrOperatorTokenProduction = new Production(
				BitwiseOrOperatorToken.class);
		Production bitwiseOrAssignmentOperatorTokenProduction = new Production(
				BitwiseOrAssignmentOperatorToken.class);
		Production logicalOrOperatorTokenProduction = new Production(
				LogicalOrOperatorToken.class);
		Production additionOperatorTokenProduction = new Production(
				AdditionOperatorToken.class);
		Production additionAssignmentOperatorTokenProduction = new Production(
				AdditionAssignmentOperatorToken.class);
		Production incrementOperatorTokenProduction = new Production(
				IncrementOperatorToken.class);
		Production subtractionOperatorTokenProduction = new Production(
				SubtractionOperatorToken.class);
		Production subtractionAssignmentOperatorTokenProduction = new Production(
				SubtractionAssignmentOperatorToken.class);
		Production decrementOperatorTokenProduction = new Production(
				DecrementOperatorToken.class);
		Production multiplicationOperatorTokenProduction = new Production(
				MultiplicationOperatorToken.class);
		Production multiplicationAssignmentOperatorTokenProduction = new Production(
				MultiplicationAssignmentOperatorToken.class);
		Production divisionOperatorTokenProduction = new Production(
				DivisionOperatorToken.class);
		Production divisionAssignmentOperatorTokenProduction = new Production(
				DivisionAssignmentOperatorToken.class);
		Production bitwiseXorOperatorTokenProduction = new Production(
				BitwiseXorOperatorToken.class);
		Production bitwiseXorAssignmentOperatorTokenProduction = new Production(
				BitwiseXorAssignmentOperatorToken.class);
		Production moduloOperatorTokenProduction = new Production(ModuloOperatorToken.class);
		Production moduloAssignmentOperatorTokenProduction = new Production(
				ModuloAssignmentOperatorToken.class);

		// Keyword Token
		Production abstractKeywordTokenProduction = new Production(AbstractKeywordToken.class);
		Production continueKeywordTokenProduction = new Production(ContinueKeywordToken.class);
		Production forKeywordTokenProduction = new Production(ForKeywordToken.class);
		Production newKeywordTokenProduction = new Production(NewKeywordToken.class);
		Production switchKeywordTokenProduction = new Production(SwitchKeywordToken.class);
		Production assertKeywordTokenProduction = new Production(AssertKeywordToken.class);
		Production defaultKeywordTokenProduction = new Production(DefaultKeywordToken.class);
		Production ifKeywordTokenProduction = new Production(IfKeywordToken.class);
		Production packageKeywordTokenProduction = new Production(PackageKeywordToken.class);
		Production synchronizedKeywordTokenProduction = new Production(
				SynchronizedKeywordToken.class);
		Production booleanKeywordTokenProduction = new Production(BooleanKeywordToken.class);
		Production doKeywordTokenProduction = new Production(DoKeywordToken.class);
		Production gotoKeywordTokenProduction = new Production(GotoKeywordToken.class);
		Production privateKeywordTokenProduction = new Production(PrivateKeywordToken.class);
		Production thisKeywordTokenProduction = new Production(ThisKeywordToken.class);
		Production breakKeywordTokenProduction = new Production(BreakKeywordToken.class);
		Production doubleKeywordTokenProduction = new Production(DoubleKeywordToken.class);
		Production implementsKeywordTokenProduction = new Production(
				ImplementsKeywordToken.class);
		Production protectedKeywordTokenProduction = new Production(
				ProtectedKeywordToken.class);
		Production throwKeywordTokenProduction = new Production(ThrowKeywordToken.class);
		Production byteKeywordTokenProduction = new Production(ByteKeywordToken.class);
		Production elseKeywordTokenProduction = new Production(ElseKeywordToken.class);
		Production importKeywordTokenProduction = new Production(ImportKeywordToken.class);
		Production publicKeywordTokenProduction = new Production(PublicKeywordToken.class);
		Production throwsKeywordTokenProduction = new Production(ThrowsKeywordToken.class);
		Production caseKeywordTokenProduction = new Production(CaseKeywordToken.class);
		Production enumKeywordTokenProduction = new Production(EnumKeywordToken.class);
		Production instanceofKeywordTokenProduction = new Production(
				InstanceofKeywordToken.class);
		Production returnKeywordTokenProduction = new Production(ReturnKeywordToken.class);
		Production transientKeywordTokenProduction = new Production(
				TransientKeywordToken.class);
		Production catchKeywordTokenProduction = new Production(CatchKeywordToken.class);
		Production extendsKeywordTokenProduction = new Production(ExtendsKeywordToken.class);
		Production intKeywordTokenProduction = new Production(IntKeywordToken.class);
		Production shortKeywordTokenProduction = new Production(ShortKeywordToken.class);
		Production tryKeywordTokenProduction = new Production(TryKeywordToken.class);
		Production charKeywordTokenProduction = new Production(CharKeywordToken.class);
		Production finalKeywordTokenProduction = new Production(FinalKeywordToken.class);
		Production interfaceKeywordTokenProduction = new Production(
				InterfaceKeywordToken.class);
		Production staticKeywordTokenProduction = new Production(StaticKeywordToken.class);
		Production voidKeywordTokenProduction = new Production(VoidKeywordToken.class);
		Production classKeywordTokenProduction = new Production(ClassKeywordToken.class);
		Production finallyKeywordTokenProduction = new Production(FinallyKeywordToken.class);
		Production longKeywordTokenProduction = new Production(LongKeywordToken.class);
		Production strictfpKeywordTokenProduction = new Production(StrictfpKeywordToken.class);
		Production volatileKeywordTokenProduction = new Production(VolatileKeywordToken.class);
		Production constKeywordTokenProduction = new Production(ConstKeywordToken.class);
		Production floatKeywordTokenProduction = new Production(FloatKeywordToken.class);
		Production nativeKeywordTokenProduction = new Production(NativeKeywordToken.class);
		Production superKeywordTokenProduction = new Production(SuperKeywordToken.class);
		Production whileKeywordTokenProduction = new Production(WhileKeywordToken.class);

		Production expressionNodeProduction = new Production(
				ExpressionNode.class,
				new Rule((Stack<Symbol> stack) -> {
					return new ExpressionNode();
				}, additionOperatorTokenProduction, integerLiteralTokenProduction, integerLiteralTokenProduction));

	}
}
