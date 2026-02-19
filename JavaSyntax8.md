```


Modifier_Repeat -> Modifier
Modifier_Repeat -> Modifier_Repeat Modifier


_PeriodIdentifierTypeArgument_Repeat -> . Identifier
_PeriodIdentifierTypeArgument_Repeat -> . Identifier TypeArguments


_EmptySquareBrackets_Repeat -> [ ]
_EmptySquareBrackets_Repeat -> _EmptySquareBrackets_Repeat [ ]

_CommaTypeArgument_Repeat -> , TypeArgument
_CommaTypeArgument_Repeat -> _CommaTypeArgument_Repeat , TypeArgument


_CommaReferenceType_Repeat -> , ReferenceType
_CommaReferenceType_Repeat -> _CommaReferenceType_Repeat , ReferenceType



_AndReferenceType_Repeat -> & ReferenceType
_AndReferenceType_Repeat -> _AndReferenceType_Repeat & ReferenceType




_CommaElementValuePair_Repeat -> , ElementValuePair
_CommaElementValuePair_Repeat -> _CommaElementValuePair_Repeat , ElementValuePair

_ElementValuesComma_Repeat -> 
_ElementValuesComma_Repeat -> ElementValues
_ElementValuesComma_Repeat -> ,
_ElementValuesComma_Repeat -> ElementValues ,
_ElementValuesComma_Repeat -> _ElementValuesComma_Repeat ElementValues
_ElementValuesComma_Repeat -> _ElementValuesComma_Repeat ,
_ElementValuesComma_Repeat -> _ElementValuesComma_Repeat ElementValues ,

_CommaElementValue_Repeat -> ElementValue
_CommaElementValue_Repeat -> _CommaElementValue_Repeat , ElementValue







ClassBodyDeclaration_Repeat -> ClassBodyDeclaration
ClassBodyDeclaration_Repeat -> ClassBodyDeclaration_Repeat ClassBodyDeclaration


CommaVariableDeclarator_Repeat -> , VariableDeclarator
CommaVariableDeclarator_Repeat -> CommaVariableDeclarator_Repeat , VariableDeclarator



_InterfaceBodyDeclaration_Repeat -> InterfaceBodyDeclaration
_InterfaceBodyDeclaration_Repeat -> _InterfaceBodyDeclaration_Repeat InterfaceBodyDeclaration


_CommaConstantDeclarator_Repeat -> , ConstantDeclarator
_CommaConstantDeclarator_Repeat -> _CommaConstantDeclarator_Repeat , ConstantDeclarator

_VariableModifier_Repeat -> VariableModifier
_VariableModifier_Repeat -> _VariableModifier_Repeat VariableModifier



_CommaVariableDeclarator_Repeat -> , VariableDeclarator
_CommaVariableDeclarator_Repeat -> _CommaVariableDeclarator_Repeat VariableDeclarator



_CommaVariableInitializer_Repeat -> , VariableInitializer
_CommaVariableInitializer_Repeat -> _CommaVariableInitializer_Repeat , VariableInitializer
_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> 
_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> VariableInitializer _CommaVariableInitializer_Repeat
_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> ,
_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> VariableInitializer _CommaVariableInitializer_Repeat ,
_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat VariableInitializer _CommaVariableInitializer_Repeat
_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat ,
_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat VariableInitializer _CommaVariableInitializer_Repeat ,




_BlockStatement_Repeat -> BlockStatement
_BlockStatement_Repeat -> _BlockStatement_Repeat BlockStatement




_CatchClause_Repeat -> CatchClause
_CatchClause_Repeat -> _CatchClause_Repeat CatchClause




_OrQualifiedIdentifier_Repeat -> | QualifiedIdentifier
_OrQualifiedIdentifier_Repeat -> _OrQualifiedIdentifier_Repeat | QualifiedIdentifier


_SemicolonResource_Repeat -> ; Resource
_SemicolonResource_Repeat -> _SemicolonResource_Repeat ; Resource



_SwitchBlockStatementGroup_Repeat -> SwitchBlockStatementGroup
_SwitchBlockStatementGroup_Repeat -> _SwitchBlockStatementGroup_Repeat SwitchBlockStatementGroup



_SwitchLabel_Repeat -> SwitchLabel
_SwitchLabel_Repeat -> _SwitchLabel_Repeat SwitchLabel
```


```

_TypeDeclaration_Repeat -> TypeDeclaration
_TypeDeclaration_Repeat -> _TypeDeclaration_Repeat TypeDeclaration
```

_PeriodIdentifier_Repeat	-> . Identifier
_PeriodIdentifier_Repeat -> _PeriodIdentifier_Repeat . Identifier


_Annotation_Repeat -> Annotation
_Annotation_Repeat -> _Annotation_Repeat Annotation

__Annotation_RepeatEmptySquareBrackets_Repeat -> [ ]
__Annotation_RepeatEmptySquareBrackets_Repeat -> _Annotation_Repeat [ ]
__Annotation_RepeatEmptySquareBrackets_Repeat -> __Annotation_RepeatEmptySquareBrackets_Repeat [ ]
__Annotation_RepeatEmptySquareBrackets_Repeat -> __Annotation_RepeatEmptySquareBrackets_Repeat _Annotation_Repeat [ ]

_AdditionalBound_Repeat -> AdditionalBound
_AdditionalBound_Repeat -> _AdditionalBound_Repeat AdditionalBound

_ImportDeclaration_Repeat -> ImportDeclaration
_ImportDeclaration_Repeat -> _ImportDeclaration_Repeat ImportDeclaration

_PackageModifier_Repeat -> PackageModifier
_PackageModifier_Repeat -> _PackageModifier_Repeat PackageModifier

Type -> PrimitiveType
Type -> ReferenceType

PrimitiveType -> NumericType
PrimitiveType -> boolean
PrimitiveType -> _Annotation_Repeat NumericType
PrimitiveType -> _Annotation_Repeat boolean

NumericType -> IntegralType
NumericType -> FloatingPointType

IntegralType -> byte
IntegralType -> short
IntegralType -> int
IntegralType -> long
IntegralType -> char

FloatingPointType -> float
FloatingPointType -> double

ReferenceType -> ClassOrInterfaceType
ReferenceType -> TypeVariable
ReferenceType -> ArrayType

ClassOrInterfaceType -> ClassType
ClassOrInterfaceType -> InterfaceType

ClassType -> Identifier
ClassType -> _Annotation_Repeat Identifier
ClassType -> Identifier TypeArguments
ClassType -> _Annotation_Repeat Identifier TypeArguments


ClassType -> Identifier
ClassType -> _Annotation_Repeat Identifier
ClassType -> Identifier TypeArguments
ClassType -> _Annotation_Repeat Identifier TypeArguments
ClassType -> ClassOrInterfaceType . Identifier
ClassType -> ClassOrInterfaceType . _Annotation_Repeat Identifier 
ClassType -> ClassOrInterfaceType . Identifier TypeArguments
ClassType -> ClassOrInterfaceType . _Annotation_Repeat Identifier TypeArguments

ClassType -> TypeIdentifier
ClassType -> _Annotation_Repeat TypeIdentifier
ClassType -> TypeIdentifier TypeArguments
ClassType -> _Annotation_Repeat TypeIdentifier TypeArguments

ClassType -> PackageName . TypeIdentifier
ClassType -> PackageName . _Annotation_Repeat TypeIdentifier
ClassType -> PackageName . TypeIdentifier TypeArguments
ClassType -> PackageName . _Annotation_Repeat TypeIdentifier TypeArguments

ClassType -> ClassOrInterfaceType . TypeIdentifier
ClassType -> ClassOrInterfaceType . _Annotation_Repeat TypeIdentifier 
ClassType -> ClassOrInterfaceType . TypeIdentifier TypeArguments
ClassType -> ClassOrInterfaceType . _Annotation_Repeat TypeIdentifier TypeArguments




InterfaceType -> ClassType

TypeVariable -> TypeIdentifier
TypeVariable -> _Annotation_Repeat TypeIdentifier

ArrayType -> PrimitiveType Dims
ArrayType -> ClassOrInterfaceType Dims
ArrayType -> TypeVariable Dims


Dims -> [ ]
Dims -> _Annotation_Repeat [ ]
Dims -> _Annotation_Repeat [ ] __Annotation_RepeatEmptySquareBrackets_Repeat



TypeParameter -> TypeIdentifier
TypeParameter -> _TypeParameterModifier_Repeat TypeIdentifier
TypeParameter -> Identifier TypeBound
TypeParameter -> _TypeParameterModifier_Repeat TypeIdentifier ypeBound

TypeParameterModifier -> Annotation

TypeBound -> extends TypeVariable
TypeBound -> extends ClassOrInterfaceType
TypeBound -> extends ClassOrInterfaceType _AdditionalBound_Repeat


AdditionalBound -> & InterfaceType

TypeArguments -> < TypeArgumentList >


TypeArgumentList -> TypeArgument
TypeArgumentList -> TypeArgument _CommaTypeArgument_Repeat

TypeArgument -> ReferenceType
TypeArgument -> Wildcard

Wildcard -> ?
Wildcard -> _Annotation_Repeat ?
Wildcard -> ? WildcardBounds
Wildcard -> _Annotation_Repeat ? WildcardBounds

WildcardBounds -> extends ReferenceType
WildcardBounds -> super ReferenceType

WildcardBounds:
extends ReferenceType
super ReferenceType



ModuleName -> Identifier
ModuleName -> ModuleName . Identifier

PackageName -> Identifier
PackageName -> PackageName . Identifier

TypeName -> Identifier
TypeName -> PackageOrTypeName . Identifier

ExpressionName -> Identifier
ExpressionName -> AmbiguousName . Identifier

MethodName -> UnqualifiedMethodIdentifier

PackageOrTypeName -> Identifier
PackageOrTypeName -> PackageOrTypeName . Identifier

AmbiguousName -> Identifier
AmbiguousName -> AmbiguousName . Identifier




















CompilationUnit -> OrdinaryCompilationUnit
CompilationUnit -> ModularCompilationUnit

OrdinaryCompilationUnit -> 
OrdinaryCompilationUnit -> PackageDeclaration
OrdinaryCompilationUnit -> _ImportDeclaration_Repeat
OrdinaryCompilationUnit -> PackageDeclaration _ImportDeclaration_Repeat
OrdinaryCompilationUnit -> TopLevelClassOrInterfaceDeclaration
OrdinaryCompilationUnit -> PackageDeclaration TopLevelClassOrInterfaceDeclaration
OrdinaryCompilationUnit -> _ImportDeclaration_Repeat TopLevelClassOrInterfaceDeclaration
OrdinaryCompilationUnit -> PackageDeclaration _ImportDeclaration_Repeat TopLevelClassOrInterfaceDeclaration

_TopLevelClassOrInterfaceDeclaration_Repeat -> TopLevelClassOrInterfaceDeclaration
_TopLevelClassOrInterfaceDeclaration_Repeat -> _TopLevelClassOrInterfaceDeclaration_Repeat TopLevelClassOrInterfaceDeclaration

ModularCompilationUnit -> ModuleDeclaration
ModularCompilationUnit -> _ImportDeclaration_Repeat ModuleDeclaration

PackageDeclaration -> package Identifier ;
PackageDeclaration -> _PackageModifier_Repeat package Identifier ;
PackageDeclaration -> package Identifier _PeriodIdentifier_Repeat ;
PackageDeclaration -> _PackageModifier_Repeat package Identifier _PeriodIdentifier_Repeat ;

PackageModifier -> Annotation

ImportDeclaration -> SingleTypeImportDeclaration
ImportDeclaration -> TypeImportOnDemandDeclaration
ImportDeclaration -> SingleStaticImportDeclaration
ImportDeclaration -> StaticImportOnDemandDeclaration

SingleTypeImportDeclaration -> import TypeName ;

TypeImportOnDemandDeclaration -> import PackageOrTypeName . * ;

SingleStaticImportDeclaration -> import static TypeName . Identifier ;

StaticImportOnDemandDeclaration -> import static TypeName . * ;

TopLevelClassOrInterfaceDeclaration -> ClassDeclaration
TopLevelClassOrInterfaceDeclaration -> InterfaceDeclaration
TopLevelClassOrInterfaceDeclaration -> ;


ModuleDeclaration -> module Identifier { }
ModuleDeclaration -> _Annotation_Repeat module Identifier { }
ModuleDeclaration -> open module Identifier { }
ModuleDeclaration -> _Annotation_Repeat open module Identifier { }
ModuleDeclaration -> module Identifier _PeriodIdentifier_Repeat { }
ModuleDeclaration -> _Annotation_Repeat module Identifier _PeriodIdentifier_Repeat { }
ModuleDeclaration -> open module Identifier _PeriodIdentifier_Repeat { }
ModuleDeclaration -> _Annotation_Repeat open module Identifier _PeriodIdentifier_Repeat { }
ModuleDeclaration -> module Identifier { _ModuleDirective_Repeat }
ModuleDeclaration -> _Annotation_Repeat module Identifier { _ModuleDirective_Repeat }
ModuleDeclaration -> open module Identifier { _ModuleDirective_Repeat }
ModuleDeclaration -> _Annotation_Repeat open module Identifier { _ModuleDirective_Repeat }
ModuleDeclaration -> module Identifier _PeriodIdentifier_Repeat { _ModuleDirective_Repeat }
ModuleDeclaration -> _Annotation_Repeat module Identifier _PeriodIdentifier_Repeat { _ModuleDirective_Repeat }
ModuleDeclaration -> open module Identifier _PeriodIdentifier_Repeat { _ModuleDirective_Repeat }
ModuleDeclaration -> _Annotation_Repeat open module Identifier _PeriodIdentifier_Repeat { _ModuleDirective_Repeat }

_ModuleDirective_Repeat -> ModuleDirective
_ModuleDirective_Repeat -> _ModuleDirective_Repeat ModuleDirective


ModuleDirective -> requires ModuleName ;
ModuleDirective -> requires {RequiresModifier} ModuleName ;
ModuleDirective -> exports PackageName ;
ModuleDirective -> exports PackageName to ModuleName ;
ModuleDirective -> exports PackageName to ModuleName _CommaModuleName_Repeat ;
ModuleDirective -> opens PackageName ;
ModuleDirective -> opens PackageName to ModuleName ;
ModuleDirective -> opens PackageName to ModuleName _CommaModuleName_Repeat ;
ModuleDirective -> provides TypeName with TypeName ;
ModuleDirective -> provides TypeName with TypeName _CommaTypeName_Repeat ;

_RequiresModifier_Repeat -> RequiresModifier
_RequiresModifier_Repeat -> _RequiresModifier_Repeat RequiresModifier

_CommaModuleName_Repeat -> , ModuleName
_CommaModuleName_Repeat -> _CommaModuleName_Repeat , ModuleName

_CommaTypeName_Repeat -> , TypeName
_CommaTypeName_Repeat -> _CommaTypeName_Repeat , TypeName

RequiresModifier -> transitive
RequiresModifier -> static




ClassDeclaration -> NormalClassDeclaration
ClassDeclaration -> EnumDeclaration
ClassDeclaration -> RecordDeclaration

NormalClassDeclaration -> class TypeIdentifier ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier ClassBody

NormalClassDeclaration -> class TypeIdentifier TypeParameters ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier TypeParameters ClassBody

NormalClassDeclaration -> class TypeIdentifier ClassExtends ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier ClassExtends ClassBody
NormalClassDeclaration -> class TypeIdentifier TypeParameters ClassExtends ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier TypeParameters ClassExtends ClassBody

NormalClassDeclaration -> class TypeIdentifier ClassImplements ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier ClassImplements ClassBody
NormalClassDeclaration -> class TypeIdentifier TypeParameters ClassImplements ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier TypeParameters ClassImplements ClassBody
NormalClassDeclaration -> class TypeIdentifier ClassExtends ClassImplements ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier ClassExtends ClassImplements ClassBody
NormalClassDeclaration -> class TypeIdentifier TypeParameters ClassExtends ClassImplements ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier TypeParameters ClassExtends ClassImplements ClassBody

NormalClassDeclaration -> class TypeIdentifier ClassPermits ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier ClassPermits ClassBody
NormalClassDeclaration -> class TypeIdentifier TypeParameters ClassPermits ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier TypeParameters ClassPermits ClassBody
NormalClassDeclaration -> class TypeIdentifier ClassExtends ClassPermits ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier ClassExtends ClassPermits ClassBody
NormalClassDeclaration -> class TypeIdentifier TypeParameters ClassExtends ClassPermits ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier TypeParameters ClassExtends ClassPermits ClassBody
NormalClassDeclaration -> class TypeIdentifier ClassImplements ClassPermits ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier ClassImplements ClassPermits ClassBody
NormalClassDeclaration -> class TypeIdentifier TypeParameters ClassImplements ClassPermits ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier TypeParameters ClassImplements ClassPermits ClassBody
NormalClassDeclaration -> class TypeIdentifier ClassExtends ClassImplements ClassPermits ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier ClassExtends ClassImplements ClassPermits ClassBody
NormalClassDeclaration -> class TypeIdentifier TypeParameters ClassExtends ClassImplements ClassPermits ClassBody
NormalClassDeclaration -> _ClassModifier_Repeat class TypeIdentifier TypeParameters ClassExtends ClassImplements ClassPermits ClassBody

_ClassModifier_Repeat -> ClassModifier
_ClassModifier_Repeat -> _ClassModifier_Repeat ClassModifier

ClassModifier -> Annotation
ClassModifier -> public
ClassModifier -> protected
ClassModifier -> private
ClassModifier -> abstract
ClassModifier -> static
ClassModifier -> final
ClassModifier -> sealed
ClassModifier -> non-sealed
ClassModifier -> strictfp


TypeParameters -> < TypeParameterList >

TypeParameterList -> TypeParameter
TypeParameterList -> TypeParameter _CommaTypeParameter_Repeat

_CommaTypeParameter_Repeat -> , TypeParameter
_CommaTypeParameter_Repeat -> _CommaTypeParameter_Repeat , TypeParameter

ClassExtends -> extends ClassType

ClassImplements -> implements InterfaceTypeList


InterfaceTypeList -> InterfaceType
InterfaceTypeList -> InterfaceType _CommaInterfaceType_Repeat

ClassPermits -> permits TypeName
ClassPermits -> permits TypeName _CommaTypeName_Repeat

ClassBody -> { }
ClassBody -> { _ClassBodyDeclaration_Repeat }

_ClassBodyDeclaration_Repeat -> ClassBodyDeclaration
_ClassBodyDeclaration_Repeat -> _ClassBodyDeclaration_Repeat ClassBodyDeclaration

ClassBodyDeclaration -> ClassMemberDeclaration
ClassBodyDeclaration -> InstanceInitializer
ClassBodyDeclaration -> StaticInitializer
ClassBodyDeclaration -> ConstructorDeclaration

ClassMemberDeclaration -> FieldDeclaration
ClassMemberDeclaration -> MethodDeclaration
ClassMemberDeclaration -> ClassDeclaration
ClassMemberDeclaration -> InterfaceDeclaration
ClassMemberDeclaration -> ;

FieldDeclaration -> UnannType VariableDeclaratorList ;
FieldDeclaration -> _FieldModifier_Repeat UnannType VariableDeclaratorList ;

_FieldModifier_Repeat -> FieldModifier
_FieldModifier_Repeat -> _FieldModifier_Repeat FieldModifier


FieldModifier -> Annotation
FieldModifier -> public
FieldModifier -> protected
FieldModifier -> private
FieldModifier -> static
FieldModifier -> final
FieldModifier -> transient
FieldModifier -> volatile

VariableDeclaratorList -> VariableDeclarator

VariableDeclaratorList:
VariableDeclarator {, VariableDeclarator}

VariableDeclarator:
VariableDeclaratorId [= VariableInitializer]
VariableDeclaratorId:
Identifier [Dims]
VariableInitializer:
Expression
ArrayInitializer
UnannType:
UnannPrimitiveType
UnannReferenceType
UnannPrimitiveType:
NumericType
boolean
UnannReferenceType:
UnannClassOrInterfaceType
UnannTypeVariable
UnannArrayType
UnannClassOrInterfaceType:
UnannClassType
UnannInterfaceType
UnannClassType:
TypeIdentifier [TypeArguments]
PackageName . {Annotation} TypeIdentifier [TypeArguments]
UnannClassOrInterfaceType . {Annotation} TypeIdentifier [TypeArguments]
UnannInterfaceType:
UnannClassType
UnannTypeVariable:
TypeIdentifier
UnannArrayType:
UnannPrimitiveType Dims
UnannClassOrInterfaceType Dims
UnannTypeVariable Dims
MethodDeclaration:
{MethodModifier} MethodHeader MethodBody
MethodModifier:
(one of)
Annotation public protected private
abstract static final synchronized native strictfp
MethodHeader:
Result MethodDeclarator [Throws]
TypeParameters {Annotation} Result MethodDeclarator [Throws]
Result:
UnannType
void
MethodDeclarator:
Identifier ( [ReceiverParameter ,] [FormalParameterList] ) [Dims]
ReceiverParameter:
{Annotation} UnannType [Identifier .] this
FormalParameterList:
FormalParameter {, FormalParameter}
FormalParameter:
{VariableModifier} UnannType VariableDeclaratorId
VariableArityParameter
VariableArityParameter:
{VariableModifier} UnannType {Annotation} ... Identifier
VariableModifier:
Annotation
final
Throws:
throws ExceptionTypeList
ExceptionTypeList:
ExceptionType {, ExceptionType}
ExceptionType:
ClassType
TypeVariable
MethodBody:
Block
;
InstanceInitializer:
Block
StaticInitializer:
static Block
ConstructorDeclaration:
{ConstructorModifier} ConstructorDeclarator [Throws] ConstructorBody
ConstructorModifier:
(one of)
Annotation public protected private
ConstructorDeclarator:
[TypeParameters] SimpleTypeName ( [ReceiverParameter ,] [FormalParameterList] )
SimpleTypeName:
TypeIdentifier
ConstructorBody:
{ [ExplicitConstructorInvocation] [BlockStatements] }
ExplicitConstructorInvocation:
[TypeArguments] this ( [ArgumentList] ) ;
[TypeArguments] super ( [ArgumentList] ) ;
ExpressionName . [TypeArguments] super ( [ArgumentList] ) ;
Primary . [TypeArguments] super ( [ArgumentList] ) ;
EnumDeclaration:
{ClassModifier} enum TypeIdentifier [ClassImplements] EnumBody
EnumBody:
{ [EnumConstantList] [,] [EnumBodyDeclarations] }
EnumConstantList:
EnumConstant {, EnumConstant}
EnumConstant:
{EnumConstantModifier} Identifier [( [ArgumentList] )] [ClassBody]
EnumConstantModifier:
Annotation
EnumBodyDeclarations:
; {ClassBodyDeclaration}
RecordDeclaration:
{ClassModifier} record TypeIdentifier [TypeParameters] RecordHeader [ClassImplements] RecordBody
RecordHeader:
( [RecordComponentList] )
RecordComponentList:
RecordComponent {, RecordComponent}
RecordComponent:
{RecordComponentModifier} UnannType Identifier
VariableArityRecordComponent
VariableArityRecordComponent:
{RecordComponentModifier} UnannType {Annotation} ... Identifier
RecordComponentModifier:
Annotation
RecordBody:
{ {RecordBodyDeclaration} }
RecordBodyDeclaration:
ClassBodyDeclaration
CompactConstructorDeclaration
CompactConstructorDeclaration:
{ConstructorModifier} SimpleTypeName ConstructorBody
Productions from §9 (Interfaces)

InterfaceDeclaration:
NormalInterfaceDeclaration
AnnotationInterfaceDeclaration
NormalInterfaceDeclaration:
{InterfaceModifier} interface TypeIdentifier [TypeParameters] [InterfaceExtends] [InterfacePermits] InterfaceBody
InterfaceModifier:
(one of)
Annotation public protected private
abstract static sealed non-sealed strictfp
InterfaceExtends:
extends InterfaceTypeList
InterfacePermits:
permits TypeName {, TypeName}
InterfaceBody:
{ {InterfaceMemberDeclaration} }
InterfaceMemberDeclaration:
ConstantDeclaration
InterfaceMethodDeclaration
ClassDeclaration
InterfaceDeclaration
;
ConstantDeclaration:
{ConstantModifier} UnannType VariableDeclaratorList ;
ConstantModifier:
(one of)
Annotation public
static final
InterfaceMethodDeclaration:
{InterfaceMethodModifier} MethodHeader MethodBody
InterfaceMethodModifier:
(one of)
Annotation public private
abstract default static strictfp
AnnotationInterfaceDeclaration:
{InterfaceModifier} @ interface TypeIdentifier AnnotationInterfaceBody
AnnotationInterfaceBody:
{ {AnnotationInterfaceMemberDeclaration} }
AnnotationInterfaceMemberDeclaration:
AnnotationInterfaceElementDeclaration
ConstantDeclaration
ClassDeclaration
InterfaceDeclaration
;
AnnotationInterfaceElementDeclaration:
{AnnotationInterfaceElementModifier} UnannType Identifier ( ) [Dims] [DefaultValue] ;
AnnotationInterfaceElementModifier:
(one of)
Annotation public
abstract
DefaultValue:
default ElementValue
Annotation:
NormalAnnotation
MarkerAnnotation
SingleElementAnnotation
NormalAnnotation:
@ TypeName ( [ElementValuePairList] )
ElementValuePairList:
ElementValuePair {, ElementValuePair}
ElementValuePair:
Identifier = ElementValue
ElementValue:
ConditionalExpression
ElementValueArrayInitializer
Annotation
ElementValueArrayInitializer:
{ [ElementValueList] [,] }
ElementValueList:
ElementValue {, ElementValue}
MarkerAnnotation:
@ TypeName
SingleElementAnnotation:
@ TypeName ( ElementValue )
Productions from §10 (Arrays)

ArrayInitializer:
{ [VariableInitializerList] [,] }
VariableInitializerList:
VariableInitializer {, VariableInitializer}
Productions from §14 (Blocks, Statements, and Patterns)

Block:
{ [BlockStatements] }
BlockStatements:
BlockStatement {BlockStatement}
BlockStatement:
LocalClassOrInterfaceDeclaration
LocalVariableDeclarationStatement
Statement
LocalClassOrInterfaceDeclaration:
ClassDeclaration
NormalInterfaceDeclaration
LocalVariableDeclarationStatement:
LocalVariableDeclaration ;
LocalVariableDeclaration:
{VariableModifier} LocalVariableType VariableDeclaratorList
LocalVariableType:
UnannType
var
Statement:
StatementWithoutTrailingSubstatement
LabeledStatement
IfThenStatement
IfThenElseStatement
WhileStatement
ForStatement
StatementNoShortIf:
StatementWithoutTrailingSubstatement
LabeledStatementNoShortIf
IfThenElseStatementNoShortIf
WhileStatementNoShortIf
ForStatementNoShortIf
StatementWithoutTrailingSubstatement:
Block
EmptyStatement
ExpressionStatement
AssertStatement
SwitchStatement
DoStatement
BreakStatement
ContinueStatement
ReturnStatement
SynchronizedStatement
ThrowStatement
TryStatement
YieldStatement
EmptyStatement:
;
LabeledStatement:
Identifier : Statement
LabeledStatementNoShortIf:
Identifier : StatementNoShortIf
ExpressionStatement:
StatementExpression ;
StatementExpression:
Assignment
PreIncrementExpression
PreDecrementExpression
PostIncrementExpression
PostDecrementExpression
MethodInvocation
ClassInstanceCreationExpression
IfThenStatement:
if ( Expression ) Statement
IfThenElseStatement:
if ( Expression ) StatementNoShortIf else Statement
IfThenElseStatementNoShortIf:
if ( Expression ) StatementNoShortIf else StatementNoShortIf
AssertStatement:
assert Expression ;
assert Expression : Expression ;
SwitchStatement:
switch ( Expression ) SwitchBlock
SwitchBlock:
{ SwitchRule {SwitchRule} }
{ {SwitchBlockStatementGroup} {SwitchLabel :} }
SwitchRule:
SwitchLabel -> Expression ;
SwitchLabel -> Block
SwitchLabel -> ThrowStatement
SwitchBlockStatementGroup:
SwitchLabel : {SwitchLabel :} BlockStatements
SwitchLabel:
case CaseConstant {, CaseConstant}
case null [, default]
case CasePattern [Guard]
default
CaseConstant:
ConditionalExpression
CasePattern:
Pattern
Guard:
when Expression
WhileStatement:
while ( Expression ) Statement
WhileStatementNoShortIf:
while ( Expression ) StatementNoShortIf
DoStatement:
do Statement while ( Expression ) ;
ForStatement:
BasicForStatement
EnhancedForStatement
ForStatementNoShortIf:
BasicForStatementNoShortIf
EnhancedForStatementNoShortIf
BasicForStatement:
for ( [ForInit] ; [Expression] ; [ForUpdate] ) Statement
BasicForStatementNoShortIf:
for ( [ForInit] ; [Expression] ; [ForUpdate] ) StatementNoShortIf
ForInit:
StatementExpressionList
LocalVariableDeclaration
ForUpdate:
StatementExpressionList
StatementExpressionList:
StatementExpression {, StatementExpression}
EnhancedForStatement:
for ( LocalVariableDeclaration : Expression ) Statement
EnhancedForStatementNoShortIf:
for ( LocalVariableDeclaration : Expression ) StatementNoShortIf
BreakStatement:
break [Identifier] ;
YieldStatement:
yield Expression ;
ContinueStatement:
continue [Identifier] ;
ReturnStatement:
return [Expression] ;
ThrowStatement:
throw Expression ;
SynchronizedStatement:
synchronized ( Expression ) Block
TryStatement:
try Block Catches
try Block [Catches] Finally
TryWithResourcesStatement
Catches:
CatchClause {CatchClause}
CatchClause:
catch ( CatchFormalParameter ) Block
CatchFormalParameter:
{VariableModifier} CatchType VariableDeclaratorId
CatchType:
UnannClassType {| ClassType}
Finally:
finally Block
TryWithResourcesStatement:
try ResourceSpecification Block [Catches] [Finally]
ResourceSpecification:
( ResourceList [;] )
ResourceList:
Resource {; Resource}
Resource:
LocalVariableDeclaration
VariableAccess
Pattern:
TypePattern
RecordPattern
TypePattern:
LocalVariableDeclaration
RecordPattern:
ReferenceType ( [PatternList] )
PatternList:
Pattern {, Pattern }
Productions from §15 (Expressions)

Primary:
PrimaryNoNewArray
ArrayCreationExpression
PrimaryNoNewArray:
Literal
ClassLiteral
this
TypeName . this
( Expression )
ClassInstanceCreationExpression
FieldAccess
ArrayAccess
MethodInvocation
MethodReference
ClassLiteral:
TypeName {[ ]} . class
NumericType {[ ]} . class
boolean {[ ]} . class
void . class
ClassInstanceCreationExpression:
UnqualifiedClassInstanceCreationExpression
ExpressionName . UnqualifiedClassInstanceCreationExpression
Primary . UnqualifiedClassInstanceCreationExpression
UnqualifiedClassInstanceCreationExpression:
new [TypeArguments] ClassOrInterfaceTypeToInstantiate ( [ArgumentList] ) [ClassBody]
ClassOrInterfaceTypeToInstantiate:
{Annotation} Identifier {. {Annotation} Identifier} [TypeArgumentsOrDiamond]
TypeArgumentsOrDiamond:
TypeArguments
<>
ArrayCreationExpression:
ArrayCreationExpressionWithoutInitializer
ArrayCreationExpressionWithInitializer
ArrayCreationExpressionWithoutInitializer:
new PrimitiveType DimExprs [Dims]
new ClassOrInterfaceType DimExprs [Dims]
ArrayCreationExpressionWithInitializer:
new PrimitiveType Dims ArrayInitializer
new ClassOrInterfaceType Dims ArrayInitializer
DimExprs:
DimExpr {DimExpr}
DimExpr:
{Annotation} [ Expression ]
ArrayAccess:
ExpressionName [ Expression ]
PrimaryNoNewArray [ Expression ]
ArrayCreationExpressionWithInitializer [ Expression ]
FieldAccess:
Primary . Identifier
super . Identifier
TypeName . super . Identifier
MethodInvocation:
MethodName ( [ArgumentList] )
TypeName . [TypeArguments] Identifier ( [ArgumentList] )
ExpressionName . [TypeArguments] Identifier ( [ArgumentList] )
Primary . [TypeArguments] Identifier ( [ArgumentList] )
super . [TypeArguments] Identifier ( [ArgumentList] )
TypeName . super . [TypeArguments] Identifier ( [ArgumentList] )
ArgumentList:
Expression {, Expression}
MethodReference:
ExpressionName :: [TypeArguments] Identifier
Primary :: [TypeArguments] Identifier
ReferenceType :: [TypeArguments] Identifier
super :: [TypeArguments] Identifier
TypeName . super :: [TypeArguments] Identifier
ClassType :: [TypeArguments] new
ArrayType :: new
Expression:
LambdaExpression
AssignmentExpression
LambdaExpression:
LambdaParameters -> LambdaBody
LambdaParameters:
( [LambdaParameterList] )
Identifier
LambdaParameterList:
LambdaParameter {, LambdaParameter}
Identifier {, Identifier}
LambdaParameter:
{VariableModifier} LambdaParameterType VariableDeclaratorId
VariableArityParameter
LambdaParameterType:
UnannType
var
LambdaBody:
Expression
Block
AssignmentExpression:
ConditionalExpression
Assignment
Assignment:
LeftHandSide AssignmentOperator Expression
LeftHandSide:
ExpressionName
FieldAccess
ArrayAccess
AssignmentOperator:
(one of)
=  *=  /=  %=  +=  -=  <<=  >>=  >>>=  &=  ^=  |=
ConditionalExpression:
ConditionalOrExpression
ConditionalOrExpression ? Expression : ConditionalExpression
ConditionalOrExpression ? Expression : LambdaExpression
ConditionalOrExpression:
ConditionalAndExpression
ConditionalOrExpression || ConditionalAndExpression
ConditionalAndExpression:
InclusiveOrExpression
ConditionalAndExpression && InclusiveOrExpression
InclusiveOrExpression:
ExclusiveOrExpression
InclusiveOrExpression | ExclusiveOrExpression
ExclusiveOrExpression:
AndExpression
ExclusiveOrExpression ^ AndExpression
AndExpression:
EqualityExpression
AndExpression & EqualityExpression
EqualityExpression:
RelationalExpression
EqualityExpression == RelationalExpression
EqualityExpression != RelationalExpression
RelationalExpression:
ShiftExpression
RelationalExpression < ShiftExpression
RelationalExpression > ShiftExpression
RelationalExpression <= ShiftExpression
RelationalExpression >= ShiftExpression
InstanceofExpression
InstanceofExpression:
RelationalExpression instanceof ReferenceType
RelationalExpression instanceof Pattern
ShiftExpression:
AdditiveExpression
ShiftExpression << AdditiveExpression
ShiftExpression >> AdditiveExpression
ShiftExpression >>> AdditiveExpression
AdditiveExpression:
MultiplicativeExpression
AdditiveExpression + MultiplicativeExpression
AdditiveExpression - MultiplicativeExpression
MultiplicativeExpression:
UnaryExpression
MultiplicativeExpression * UnaryExpression
MultiplicativeExpression / UnaryExpression
MultiplicativeExpression % UnaryExpression
UnaryExpression:
PreIncrementExpression
PreDecrementExpression
+ UnaryExpression
- UnaryExpression
UnaryExpressionNotPlusMinus
PreIncrementExpression:
++ UnaryExpression
PreDecrementExpression:
-- UnaryExpression
UnaryExpressionNotPlusMinus:
PostfixExpression
~ UnaryExpression
! UnaryExpression
CastExpression
SwitchExpression
PostfixExpression:
Primary
ExpressionName
PostIncrementExpression
PostDecrementExpression
PostIncrementExpression:
PostfixExpression ++
PostDecrementExpression:
PostfixExpression --
CastExpression:
( PrimitiveType ) UnaryExpression
( ReferenceType {AdditionalBound} ) UnaryExpressionNotPlusMinus
( ReferenceType {AdditionalBound} ) LambdaExpression
SwitchExpression:
switch ( Expression ) SwitchBlock
ConstantExpression:
Expression




















QualifiedIdentifier:
    Identifier { . Identifier }
    
```
QualifiedIdentifier -> Identifier _PeriodIdentifier_Repeat
```

QualifiedIdentifierList: 
    QualifiedIdentifier { , QualifiedIdentifier }

```
QualifiedIdentifierList -> QualifiedIdentifier
QualifiedIdentifierList -> QualifiedIdentifierList , QualifiedIdentifier
```

CompilationUnit: 
    [[Annotations] package QualifiedIdentifier ;]
                                {ImportDeclaration} {TypeDeclaration}

```
CompilationUnit -> 
CompilationUnit -> package QualifiedIdentifier ;
CompilationUnit -> Annotations package QualifiedIdentifier ;

CompilationUnit -> _ImportDeclaration_Repeat
CompilationUnit -> package QualifiedIdentifier ; _ImportDeclaration_Repeat
CompilationUnit -> Annotations package QualifiedIdentifier ; _ImportDeclaration_Repeat

CompilationUnit -> _TypeDeclaration_Repeat
CompilationUnit -> package QualifiedIdentifier ; _TypeDeclaration_Repeat
CompilationUnit -> Annotations package QualifiedIdentifier ; _TypeDeclaration_Repeat

CompilationUnit -> _ImportDeclaration_Repeat _TypeDeclaration_Repeat
CompilationUnit -> package QualifiedIdentifier ; _ImportDeclaration_Repeat _TypeDeclaration_Repeat
CompilationUnit -> Annotations package QualifiedIdentifier ; _ImportDeclaration_Repeat _TypeDeclaration_Repeat
```

ImportDeclaration: 
    import [static] Identifier { . Identifier } [. *] ;

```
ImportDeclaration -> import Identifier ;
ImportDeclaration -> import static Identifier ;
ImportDeclaration -> import Identifier . * ;
ImportDeclaration -> import static Identifier . * ;

ImportDeclaration -> import Identifier _PeriodIdentifier_Repeat ;
ImportDeclaration -> import static Identifier _PeriodIdentifier_Repeat ;
ImportDeclaration -> import Identifier _PeriodIdentifier_Repeat . * ;
ImportDeclaration -> import static Identifier _PeriodIdentifier_Repeat . * ;
```

TypeDeclaration: 
    ClassOrInterfaceDeclaration
    ;
    
```
TypeDeclaration -> ClassOrInterfaceDeclaration
TypeDeclaration -> ;
```

ClassOrInterfaceDeclaration: 
    {Modifier} (ClassDeclaration | InterfaceDeclaration)

```

ClassOrInterfaceDeclaration -> ClassDeclaration
ClassOrInterfaceDeclaration -> InterfaceDeclaration
ClassOrInterfaceDeclaration -> Modifier_Repeat ClassDeclaration
ClassOrInterfaceDeclaration -> Modifier_Repeat InterfaceDeclaration
```

ClassDeclaration: 
    NormalClassDeclaration
    EnumDeclaration

```
ClassDeclaration -> NormalClassDeclaration
ClassDeclaration -> EnumDeclaration
```

InterfaceDeclaration: 
    NormalInterfaceDeclaration
    AnnotationTypeDeclaration

```
InterfaceDeclaration -> NormalInterfaceDeclaration
InterfaceDeclaration -> AnnotationTypeDeclaration
```


NormalClassDeclaration: 
    class Identifier [TypeParameters]
                                [extends Type] [implements TypeList] ClassBody

```
NormalClassDeclaration -> class Identifier ClassBody
NormalClassDeclaration -> class Identifier TypeParameters ClassBody
NormalClassDeclaration -> class Identifier extends Type ClassBody
NormalClassDeclaration -> class Identifier TypeParameters extends Type ClassBody

NormalClassDeclaration -> class Identifier implements TypeList ClassBody
NormalClassDeclaration -> class Identifier TypeParameters implements TypeList ClassBody
NormalClassDeclaration -> class Identifier extends Type implements TypeList ClassBody
NormalClassDeclaration -> class Identifier TypeParameters extends Type implements TypeList ClassBody
```

EnumDeclaration:
    enum Identifier [implements TypeList] EnumBody

```
EnumDeclaration -> enum Identifier EnumBody
EnumDeclaration -> enum Identifier implements TypeList EnumBody
```

NormalInterfaceDeclaration: 
    interface Identifier [TypeParameters] [extends TypeList] InterfaceBody

```
NormalInterfaceDeclaration -> interface Identifier InterfaceBody
NormalInterfaceDeclaration -> interface Identifier TypeParameters InterfaceBody
NormalInterfaceDeclaration -> interface Identifier extends TypeList InterfaceBody
NormalInterfaceDeclaration -> interface Identifier TypeParameters extends TypeList InterfaceBody
```

AnnotationTypeDeclaration:
    @ interface Identifier AnnotationTypeBody

```
AnnotationTypeDeclaration -> @ interface Identifier AnnotationTypeBody
```


Type:
    BasicType {[]}
    ReferenceType  {[]}

```
Type -> BasicType
Type -> BasicType _EmptySquareBrackets_Repeat
Type -> ReferenceType
Type -> ReferenceType _EmptySquareBrackets_Repeat
```

BasicType: 
    byte
    short
    char
    int
    long
    float
    double
    boolean

```
BasicType -> byte
BasicType -> short
BasicType -> char
BasicType -> int
BasicType -> long
BasicType -> float
BasicType -> double
BasicType -> boolean
```

ReferenceType:
    Identifier [TypeArguments] { . Identifier [TypeArguments] }

```
ReferenceType -> Identifier
ReferenceType -> Identifier TypeArguments
ReferenceType -> Identifier _PeriodIdentifierTypeArgument_Repeat
ReferenceType -> Identifier TypeArguments _PeriodIdentifierTypeArgument_Repeat
```



TypeArguments: 
    < TypeArgument { , TypeArgument } >
    
```
TypeArguments -> < TypeArgument >
TypeArguments -> < TypeArgument _CommaTypeArgument_Repeat >
```

-------------------------------------------------------------------------
TypeArgument:  
    ReferenceType
    ? [ (extends | super) ReferenceType ]

```
TypeArgument -> ReferenceType
TypeArgument -> ?
TypeArgument -> ? extends ReferenceType
TypeArgument -> ? super ReferenceType
```

NonWildcardTypeArguments:
    < TypeList >

```
NonWildcardTypeArguments -> < TypeList >
```

TypeList:  
    ReferenceType { , ReferenceType }

```
TypeList -> ReferenceType
TypeList -> ReferenceType _CommaReferenceType_Repeat
```


TypeArgumentsOrDiamond:
    < > 
    TypeArguments

```
TypeArgumentsOrDiamond -> < >
TypeArgumentsOrDiamond -> TypeArguments
```

NonWildcardTypeArgumentsOrDiamond:
    < >
    NonWildcardTypeArguments

```
NonWildcardTypeArgumentsOrDiamond -> < >
NonWildcardTypeArgumentsOrDiamond -> NonWildcardTypeArguments
```

TypeParameters:
    < TypeParameter { , TypeParameter } >

```
TypeParameters -> < TypeParameter >
TypeParameters -> < TypeParameter _CommaTypeParameter_Repeat >
```

TypeParameter:
    Identifier [extends Bound]

```
TypeParameter -> Identifier
TypeParameter -> Identifier extends Bound
```

Bound:  
    ReferenceType { & ReferenceType }

```
Bound -> ReferenceType
Bound -> ReferenceType _AndReferenceType_Repeat
```

Modifier: 
    Annotation
    public
    protected
    private
    static 
    abstract
    final
    native
    synchronized
    transient
    volatile
    strictfp

```
Modifier -> Annotation
Modifier -> public
Modifier -> protected
Modifier -> private
Modifier -> static
Modifier -> abstract
Modifier -> final
Modifier -> native
Modifier -> synchronized
Modifier -> transient
Modifier -> volatile
Modifier -> strictfp
```

Annotations:
    Annotation {Annotation}

```
Annotations -> Annotation
Annotations -> Annotation _Annotation_Repeat
```

Annotation:
    @ QualifiedIdentifier [ ( [AnnotationElement] ) ]

```
Annotation -> @ QualifiedIdentifier
Annotation -> @ QualifiedIdentifier ( )
Annotation -> @ QualifiedIdentifier ( AnnotationElement )
```

AnnotationElement:
    ElementValuePairs
    ElementValue

```
AnnotationElement -> ElementValuePairs
AnnotationElement -> ElementValue
```

ElementValuePairs:
    ElementValuePair { , ElementValuePair }

```
ElementValuePairs -> ElementValuePair
ElementValuePairs -> ElementValuePair _CommaElementValuePair_Repeat
```

ElementValuePair:
    Identifier = ElementValue

```
ElementValuePair -> Identifier = ElementValue
```
    
ElementValue:
    Annotation
    Expression1 
    ElementValueArrayInitializer

```
ElementValue -> Annotation
ElementValue -> Expression1
ElementValue -> ElementValueArrayInitializer
```

ElementValueArrayInitializer:
    { [ElementValues] [,] }

```
ElementValueArrayInitializer ->
ElementValueArrayInitializer -> _ElementValuesComma_Repeat
```

ElementValues:
    ElementValue { , ElementValue }

```
ElementValues -> ElementValue
ElementValues -> ElementValue _CommaElementValue_Repeat
```


-------------------------------------------------------------------------------------

ClassBody: 
    { { ClassBodyDeclaration } }
    
```
ClassBody -> { }
ClassBody -> { ClassBodyDeclaration_Repeat }
```
    

ClassBodyDeclaration:
    ; 
    {Modifier} MemberDecl
    [static] Block

```
ClassBodyDeclaration -> ;
ClassBodyDeclaration -> MemberDecl
ClassBodyDeclaration -> Modifier_Repeat MemberDecl
ClassBodyDeclaration -> Block
ClassBodyDeclaration -> static Block
```

MemberDecl:
    MethodOrFieldDecl
    void Identifier VoidMethodDeclaratorRest
    Identifier ConstructorDeclaratorRest
    GenericMethodOrConstructorDecl
    ClassDeclaration
    InterfaceDeclaration

```
MemberDecl -> MethodOrFieldDecl
MemberDecl -> void Identifier VoidMethodDeclaratorRest
MemberDecl -> Identifier ConstructorDeclaratorRest
MemberDecl -> GenericMethodOrConstructorDecl
MemberDecl -> ClassDeclaration
MemberDecl -> InterfaceDeclaration
```


MethodOrFieldDecl:
    Type Identifier MethodOrFieldRest

```
MethodOrFieldDecl -> Type Identifier MethodOrFieldRest
```


MethodOrFieldRest:  
    FieldDeclaratorsRest ;
    MethodDeclaratorRest

```
MethodOrFieldRest -> FieldDeclaratorsRest ;
MethodOrFieldRest -> MethodDeclaratorRest
```


FieldDeclaratorsRest:  
    VariableDeclaratorRest { , VariableDeclarator }

```
FieldDeclaratorsRest -> VariableDeclaratorRest
FieldDeclaratorsRest -> VariableDeclaratorRest CommaVariableDeclarator_Repeat
```

MethodDeclaratorRest:
    FormalParameters {[]} [throws QualifiedIdentifierList] (Block | ;)

```
MethodDeclaratorRest -> FormalParameters _EmptySquareBrackets_Repeat Block
MethodDeclaratorRest -> FormalParameters _EmptySquareBrackets_Repeat ;
MethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList _EmptySquareBrackets_Repeat Block
MethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList _EmptySquareBrackets_Repeat ;

MethodDeclaratorRest -> FormalParameters Block
MethodDeclaratorRest -> FormalParameters ;
MethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList Block
MethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList ;
```

VoidMethodDeclaratorRest:
    FormalParameters [throws QualifiedIdentifierList] (Block | ;)

```
VoidMethodDeclaratorRest -> FormalParameters Block
VoidMethodDeclaratorRest -> FormalParameters ;
VoidMethodDeclaratorRest -> throws QualifiedIdentifierList FormalParameters Block
VoidMethodDeclaratorRest -> throws QualifiedIdentifierList FormalParameters ;
```

ConstructorDeclaratorRest:
    FormalParameters [throws QualifiedIdentifierList] Block

```
ConstructorDeclaratorRest -> FormalParameters Block
ConstructorDeclaratorRest -> FormalParameters throws QualifiedIdentifierList Block
```


GenericMethodOrConstructorDecl:
    TypeParameters GenericMethodOrConstructorRest

```
GenericMethodOrConstructorDecl -> TypeParameters GenericMethodOrConstructorRest
```

GenericMethodOrConstructorRest:
    (Type | void) Identifier MethodDeclaratorRest
    Identifier ConstructorDeclaratorRest

```
GenericMethodOrConstructorRest -> Type Identifier MethodDeclaratorRest
GenericMethodOrConstructorRest -> void Identifier MethodDeclaratorRest
GenericMethodOrConstructorRest -> Identifier ConstructorDeclaratorRest
```

InterfaceBody: 
    { { InterfaceBodyDeclaration } }

```
InterfaceBody -> { }
InterfaceBody -> { _InterfaceBodyDeclaration_Repeat }
```

InterfaceBodyDeclaration:
    ; 
    {Modifier} InterfaceMemberDecl

```
InterfaceBodyDeclaration -> ;
InterfaceBodyDeclaration -> InterfaceMemberDecl
InterfaceBodyDeclaration -> Modifier_Repeat InterfaceMemberDecl
```


InterfaceMemberDecl:
    InterfaceMethodOrFieldDecl
    void Identifier VoidInterfaceMethodDeclaratorRest
    InterfaceGenericMethodDecl
    ClassDeclaration
    InterfaceDeclaration

```
InterfaceMemberDecl -> InterfaceMethodOrFieldDecl
InterfaceMemberDecl -> void Identifier VoidInterfaceMethodDeclaratorRest
InterfaceMemberDecl -> InterfaceGenericMethodDecl
InterfaceMemberDecl -> ClassDeclaration
InterfaceMemberDecl -> InterfaceDeclaration
```

InterfaceMethodOrFieldDecl:
    Type Identifier InterfaceMethodOrFieldRest

```
InterfaceMethodOrFieldDecl -> Type Identifier InterfaceMethodOrFieldRest
```


InterfaceMethodOrFieldRest:
    ConstantDeclaratorsRest ;
    InterfaceMethodDeclaratorRest


```
InterfaceMethodOrFieldRest -> ConstantDeclaratorsRest ;
InterfaceMethodOrFieldRest -> InterfaceMethodDeclaratorRest
```

ConstantDeclaratorsRest: 
    ConstantDeclaratorRest { , ConstantDeclarator }

```
ConstantDeclaratorsRest -> ConstantDeclaratorRest
ConstantDeclaratorsRest -> ConstantDeclaratorRest _CommaConstantDeclarator_Repeat
```

ConstantDeclaratorRest: 
    {[]} = VariableInitializer

```
ConstantDeclaratorRest -> = VariableInitializer
ConstantDeclaratorRest -> _EmptySquareBrackets_Repeat = VariableInitializer
```

ConstantDeclarator: 
    Identifier ConstantDeclaratorRest


```
ConstantDeclarator -> Identifier ConstantDeclaratorRest
```

InterfaceMethodDeclaratorRest:
    FormalParameters {[]} [throws QualifiedIdentifierList] ; 

```
InterfaceMethodDeclaratorRest -> FormalParameters ;
InterfaceMethodDeclaratorRest -> FormalParameters _EmptySquareBrackets_Repeat ;
InterfaceMethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList ;
InterfaceMethodDeclaratorRest -> FormalParameters _EmptySquareBrackets_Repeat throws QualifiedIdentifierList ;
```

VoidInterfaceMethodDeclaratorRest:
    FormalParameters [throws QualifiedIdentifierList] ;  

```
VoidInterfaceMethodDeclaratorRest -> FormalParameters ;  
VoidInterfaceMethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList ;  
```

InterfaceGenericMethodDecl:
    TypeParameters (Type | void) Identifier InterfaceMethodDeclaratorRest

```
InterfaceGenericMethodDecl -> TypeParameters Type Identifier InterfaceMethodDeclaratorRest
InterfaceGenericMethodDecl -> TypeParameters void Identifier InterfaceMethodDeclaratorRest
```



FormalParameters: 
    ( [FormalParameterDecls] )


```
FormalParameters -> ( )
FormalParameters -> ( FormalParameterDecls )
```

FormalParameterDecls: 
    {VariableModifier}  Type FormalParameterDeclsRest

```
FormalParameterDecls -> Type FormalParameterDeclsRest
FormalParameterDecls -> _VariableModifier_Repeat Type FormalParameterDeclsRest
```

VariableModifier:
    final
    Annotation

```
VariableModifier -> final
VariableModifier -> Annotation
```

FormalParameterDeclsRest: 
    VariableDeclaratorId [, FormalParameterDecls]
    ... VariableDeclaratorId

```
FormalParameterDeclsRest -> VariableDeclaratorId
FormalParameterDeclsRest -> VariableDeclaratorId , FormalParameterDecls
FormalParameterDeclsRest -> ... VariableDeclaratorId
```

VariableDeclaratorId:
    Identifier {[]}

```
VariableDeclaratorId -> Identifier
VariableDeclaratorId -> _EmptySquareBrackets_Repeat Identifier
```

VariableDeclarators:
    VariableDeclarator { , VariableDeclarator }

```
VariableDeclarators -> VariableDeclarator
VariableDeclarators -> VariableDeclarator _CommaVariableDeclarator_Repeat
```

VariableDeclarator:
    Identifier VariableDeclaratorRest

```
VariableDeclarator -> Identifier VariableDeclaratorRest
```

VariableDeclaratorRest:
    {[]} [ = VariableInitializer ]

```
VariableDeclaratorRest -> 
VariableDeclaratorRest -> _EmptySquareBrackets_Repeat
VariableDeclaratorRest -> = VariableInitializer
VariableDeclaratorRest -> _EmptySquareBrackets_Repeat = VariableInitializer
```

VariableInitializer:
    ArrayInitializer
    Expression

```
VariableInitializer -> ArrayInitializer
VariableInitializer -> Expression
```

ArrayInitializer:
    { [ VariableInitializer { , VariableInitializer } [,] ] }

```
ArrayInitializer -> 
ArrayInitializer -> _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat
```

Block: 
    { BlockStatements }

```
Block -> { BlockStatements }
```

BlockStatements: 
    { BlockStatement }

```
BlockStatements ->
BlockStatements -> _BlockStatement_Repeat
```

BlockStatement:
    LocalVariableDeclarationStatement
    ClassOrInterfaceDeclaration
    [Identifier :] Statement

```
BlockStatement -> LocalVariableDeclarationStatement
BlockStatement -> ClassOrInterfaceDeclaration
BlockStatement -> Statement
BlockStatement -> Identifier : Statement
```

LocalVariableDeclarationStatement:
    { VariableModifier }  Type VariableDeclarators ;

```
LocalVariableDeclarationStatement -> Type VariableDeclarators ;
LocalVariableDeclarationStatement -> _VariableModifier_Repeat Type VariableDeclarators ;
```

Statement:
    Block
    ;
    Identifier : Statement
    StatementExpression ;
    if ParExpression Statement [else Statement] 
    assert Expression [: Expression] ;
    switch ParExpression { SwitchBlockStatementGroups } 
    while ParExpression Statement
    do Statement while ParExpression ;
    for ( ForControl ) Statement
    break [Identifier] ;
    continue [Identifier] ;
    return [Expression] ;
    throw Expression ;
    synchronized ParExpression Block
    try Block (Catches | [Catches] Finally)
    try ResourceSpecification Block [Catches] [Finally]

```
Statement -> Block
Statement -> Identifier : Statement
Statement -> StatementExpression ;
Statement -> if ParExpression Statement
Statement -> if ParExpression Statement else Statement
Statement -> assert Expression ;
Statement -> assert Expression : Expression ;
Statement -> switch ParExpression { SwitchBlockStatementGroups } 
Statement -> while ParExpression Statement
Statement -> do Statement while ParExpression ;
Statement -> for ( ForControl ) Statement
Statement -> break ;
Statement -> break Identifier ;
Statement -> continue ;
Statement -> continue Identifier ;
Statement -> return ;
Statement -> return Expression ;
Statement -> throw Expression ;
Statement -> synchronized ParExpression Block
Statement -> try Block Catches
Statement -> try Block Finally
Statement -> try Block Catches Finally
Statement -> try ResourceSpecification Block
Statement -> try ResourceSpecification Block Catches
Statement -> try ResourceSpecification Block Finally
Statement -> try ResourceSpecification Block Catches Finally
```


StatementExpression: 
    Expression

```
StatementExpression -> Expression
```

Catches:
    CatchClause { CatchClause }

-------------------------------------------

```
Catches -> CatchClause
Catches -> CatchClause _CatchClause_Repeat
```

CatchClause:  
    catch ( {VariableModifier} CatchType Identifier ) Block

```
CatchClause -> catch ( CatchType Identifier ) Block
CatchClause -> catch ( _VariableModifier_Repeat CatchType Identifier ) Block
```

CatchType:
    QualifiedIdentifier { | QualifiedIdentifier }

```
CatchType -> QualifiedIdentifier
CatchType -> QualifiedIdentifier _OrQualifiedIdentifier_Repeat
```

Finally:
    finally Block

```
Finally -> finally Block
```

ResourceSpecification:
    ( Resources [;] )

```
ResourceSpecification -> ( Resources )
ResourceSpecification -> ( Resources ; )
```

Resources:
    Resource { ; Resource }

```
Resources -> Resource _SemicolonResource_Repeat
```

Resource:
    {VariableModifier} ReferenceType VariableDeclaratorId = Expression 

```
Resource -> ReferenceType VariableDeclaratorId = Expression
Resource -> _VariableModifier_Repeat ReferenceType VariableDeclaratorId = Expression
```


SwitchBlockStatementGroups: 
    { SwitchBlockStatementGroup }

```
SwitchBlockStatementGroups -> 
SwitchBlockStatementGroups -> _SwitchBlockStatementGroup_Repeat
```


SwitchBlockStatementGroup: 
    SwitchLabels BlockStatements
    
```
SwitchBlockStatementGroup -> SwitchLabels BlockStatements
```

SwitchLabels:
    SwitchLabel { SwitchLabel }

```
SwitchLabels -> SwitchLabel
SwitchLabels -> SwitchLabel _SwitchLabel_Repeat
```

SwitchLabel: 
    case Expression :
    case EnumConstantName :
    default :

```
SwitchLabel -> case Expression :
switchLabel -> case EnumConstantName :
switchLabel -> default :
```

EnumConstantName:
    Identifier


```
EnumConstantName -> Identifier
```



ForControl:
    ForVarControl
    ForInit ; [Expression] ; [ForUpdate]
    
```
ForControl -> ForVarControl
ForControl -> ForInit ; ; 
ForControl -> ForInit ; Expression ; 
ForControl -> ForInit ; ForUpdate ; 
ForControl -> ForInit ; Expression ; ForUpdate
```

ForVarControl:
    {VariableModifier} Type VariableDeclaratorId  ForVarControlRest

``


ForVarControlRest:
    ForVariableDeclaratorsRest ; [Expression] ; [ForUpdate]
    : Expression

ForVariableDeclaratorsRest:
    [= VariableInitializer] { , VariableDeclarator }

ForInit: 
ForUpdate:
    StatementExpression { , StatementExpression }    


Expression: 
    Expression1 [AssignmentOperator Expression1]

AssignmentOperator: 
    = 
    +=
    -= 
    *=
    /=
    &=
    |=
    ^=
    %=
    <<=
    >>=
    >>>=

Expression1: 
    Expression2 [Expression1Rest]

Expression1Rest: 
    ? Expression : Expression1

Expression2:
    Expression3 [Expression2Rest]

Expression2Rest:
    { InfixOp Expression3 }
    instanceof Type


InfixOp: 
    || 
    &&
    |
    ^
    &
    ==
    !=
    <
    >
    <=
    >=
    <<
    >>
    >>>
    +
    -
    *
    /
    %

Expression3: 
    PrefixOp Expression3
    ( (Expression | Type) ) Expression3
    Primary { Selector } { PostfixOp }

PrefixOp: 
    ++
    --
    !
    ~
    +
    -

PostfixOp: 
    ++
    --


Primary: 
    Literal
    ParExpression
    this [Arguments]
    super SuperSuffix
    new Creator
    NonWildcardTypeArguments (ExplicitGenericInvocationSuffix | this Arguments)
    Identifier { . Identifier } [IdentifierSuffix]
    BasicType {[]} . class
    void . class



Literal:
    IntegerLiteral
    FloatingPointLiteral
    CharacterLiteral 
    StringLiteral 
    BooleanLiteral
    NullLiteral

ParExpression: 
    ( Expression )

Arguments:
    ( [ Expression { , Expression } ] )

SuperSuffix: 
    Arguments 
    . Identifier [Arguments]

ExplicitGenericInvocationSuffix: 
    super SuperSuffix
    Identifier Arguments


Creator:  
    NonWildcardTypeArguments CreatedName ClassCreatorRest
    CreatedName (ClassCreatorRest | ArrayCreatorRest)

CreatedName:   
    Identifier [TypeArgumentsOrDiamond] { . Identifier [TypeArgumentsOrDiamond] }

ClassCreatorRest: 
    Arguments [ClassBody]

ArrayCreatorRest:
    [ (] {[]} ArrayInitializer  |  Expression ] {[ Expression ]} {[]})



IdentifierSuffix:
    [ ({[]} . class | Expression) ]
    Arguments 
    . (class | ExplicitGenericInvocation | this | super Arguments |
                                new [NonWildcardTypeArguments] InnerCreator)

ExplicitGenericInvocation:
    NonWildcardTypeArguments ExplicitGenericInvocationSuffix

InnerCreator:  
    Identifier [NonWildcardTypeArgumentsOrDiamond] ClassCreatorRest



Selector:
    . Identifier [Arguments]
    . ExplicitGenericInvocation
    . this
    . super SuperSuffix
    . new [NonWildcardTypeArguments] InnerCreator
    [ Expression ]


EnumBody:
    { [EnumConstants] [,] [EnumBodyDeclarations] }

EnumConstants:
    EnumConstant
    EnumConstants , EnumConstant

EnumConstant:
    [Annotations] Identifier [Arguments] [ClassBody]

EnumBodyDeclarations:
    ; {ClassBodyDeclaration}



AnnotationTypeBody:
    { [AnnotationTypeElementDeclarations] }

AnnotationTypeElementDeclarations:
    AnnotationTypeElementDeclaration
    AnnotationTypeElementDeclarations AnnotationTypeElementDeclaration

AnnotationTypeElementDeclaration:
    {Modifier} AnnotationTypeElementRest

AnnotationTypeElementRest:
    Type Identifier AnnotationConstantRest ;
    ClassDeclaration
    InterfaceDeclaration
    EnumDeclaration  
    AnnotationTypeDeclaration

AnnotationMethodOrConstantRest:
    AnnotationMethodRest
    ConstantDeclaratorsRest  

AnnotationMethodRest:
    ( ) [[]] [default ElementValue]