```
PeriodIdentifier_Repeat	-> . Identifier
PeriodIdentifier_Repeat -> PeriodIdentifier_Repeat . Identifier

ImportDeclaration_Repeat -> ImportDeclaration
ImportDeclaration_Repeat -> ImportDeclaration_Repeat ImportDeclaration

TypeDeclaration_Repeat -> TypeDeclaration
TypeDeclaration_Repeat -> TypeDeclaration_Repeat TypeDeclaration

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

_CommaTypeParameter_Repeat -> , TypeParameter
_CommaTypeParameter_Repeat -> _CommaTypeParameter_Repeat , TypeParameter

_AndReferenceType_Repeat -> & ReferenceType
_AndReferenceType_Repeat -> _AndReferenceType_Repeat & ReferenceType


_Annotation_Repeat -> Annotation
_Annotation_Repeat -> _Annotation_Repeat Annotation

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


Identifier:
    IDENTIFIER
    
```
Identifier -> IDENTIFIER
```

QualifiedIdentifier:
    Identifier { . Identifier }
    
```
QualifiedIdentifier -> Identifier PeriodIdentifier_Repeat
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

CompilationUnit -> ImportDeclaration_Repeat
CompilationUnit -> package QualifiedIdentifier ; ImportDeclaration_Repeat
CompilationUnit -> Annotations package QualifiedIdentifier ; ImportDeclaration_Repeat

CompilationUnit -> TypeDeclaration_Repeat
CompilationUnit -> package QualifiedIdentifier ; TypeDeclaration_Repeat
CompilationUnit -> Annotations package QualifiedIdentifier ; TypeDeclaration_Repeat

CompilationUnit -> ImportDeclaration_Repeat TypeDeclaration_Repeat
CompilationUnit -> package QualifiedIdentifier ; ImportDeclaration_Repeat TypeDeclaration_Repeat
CompilationUnit -> Annotations package QualifiedIdentifier ; ImportDeclaration_Repeat TypeDeclaration_Repeat
```

ImportDeclaration: 
    import [static] Identifier { . Identifier } [. *] ;

```
ImportDeclaration -> import Identifier ;
ImportDeclaration -> import static Identifier ;
ImportDeclaration -> import Identifier . * ;
ImportDeclaration -> import static Identifier . * ;

ImportDeclaration -> import Identifier PeriodIdentifier_Repeat ;
ImportDeclaration -> import static Identifier PeriodIdentifier_Repeat ;
ImportDeclaration -> import Identifier PeriodIdentifier_Repeat . * ;
ImportDeclaration -> import static Identifier PeriodIdentifier_Repeat . * ;
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