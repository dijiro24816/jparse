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

ReferenceType_AndRepeat -> ReferenceType
ReferenceType_AndRepeat -> ReferenceType_AndRepeat & ReferenceType


Annotation_Repeat -> Annotation
Annotation_Repeat -> Annotation_Repeat Annotation

ElementValuePair_CommaRepeat -> ElementValuePair
ElementValuePair_CommaRepeat -> ElementValuePair_CommaRepeat , ElementValuePair

ElementValuesComma_Repeat -> 
ElementValuesComma_Repeat -> ElementValues
ElementValuesComma_Repeat -> ,
ElementValuesComma_Repeat -> ElementValues ,
ElementValuesComma_Repeat -> ElementValuesComma_Repeat ElementValues
ElementValuesComma_Repeat -> ElementValuesComma_Repeat ,
ElementValuesComma_Repeat -> ElementValuesComma_Repeat ElementValues ,

ElementValue_CommaRepeat -> ElementValue
ElementValue_CommaRepeat -> ElementValue_CommaRepeat , ElementValue







ClassBodyDeclaration_Repeat -> ClassBodyDeclaration
ClassBodyDeclaration_Repeat -> ClassBodyDeclaration_Repeat ClassBodyDeclaration


CommaVariableDeclarator_Repeat -> , VariableDeclarator
CommaVariableDeclarator_Repeat -> CommaVariableDeclarator_Repeat , VariableDeclarator
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
ReferenceType -> Identifier _PeriodIdentifierTypeArgument_Repeat
ReferenceType -> Identifier TypeArguments _PeriodIdentifierTypeArgument_Repeat
```



TypeArguments: 
    < TypeArgument { , TypeArgument } >
    
```
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
Bound -> ReferenceType_AndRepeat
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
Annotations -> Annotation_Repeat
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
ElementValuePairz -> ElementValuePair_CommaRepeat
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
ElementValueArrayInitializer -> ElementValuesComma_Repeat
```

ElementValues:
    ElementValue { , ElementValue }

```
ElementValues -> ElementValue_CommaRepeat
```


-------------------------------------------------------------------------------------

ClassBody: 
    { { ClassBodyDeclaration } }
    
```
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
MemberDecl -> void Identifier VoidMethodDeclarationRest
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
MethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList EmptySquareBrackets_Repeat ;
```

VoidMethodDeclaratorRest:
    FormalParameters [throws QualifiedIdentifierList] (Block | ;)

ConstructorDeclaratorRest:
    FormalParameters [throws QualifiedIdentifierList] Block

GenericMethodOrConstructorDecl:
    TypeParameters GenericMethodOrConstructorRest

GenericMethodOrConstructorRest:
    (Type | void) Identifier MethodDeclaratorRest
    Identifier ConstructorDeclaratorRest


InterfaceBody: 
    { { InterfaceBodyDeclaration } }

InterfaceBodyDeclaration:
    ; 
    {Modifier} InterfaceMemberDecl

InterfaceMemberDecl:
    InterfaceMethodOrFieldDecl
    void Identifier VoidInterfaceMethodDeclaratorRest
    InterfaceGenericMethodDecl
    ClassDeclaration
    InterfaceDeclaration

InterfaceMethodOrFieldDecl:
    Type Identifier InterfaceMethodOrFieldRest

InterfaceMethodOrFieldRest:
    ConstantDeclaratorsRest ;
    InterfaceMethodDeclaratorRest

ConstantDeclaratorsRest: 
    ConstantDeclaratorRest { , ConstantDeclarator }

ConstantDeclaratorRest: 
    {[]} = VariableInitializer

ConstantDeclarator: 
    Identifier ConstantDeclaratorRest

InterfaceMethodDeclaratorRest:
    FormalParameters {[]} [throws QualifiedIdentifierList] ; 

VoidInterfaceMethodDeclaratorRest:
    FormalParameters [throws QualifiedIdentifierList] ;  

InterfaceGenericMethodDecl:
    TypeParameters (Type | void) Identifier InterfaceMethodDeclaratorRest


FormalParameters: 
    ( [FormalParameterDecls] )

FormalParameterDecls: 
    {VariableModifier}  Type FormalParameterDeclsRest

VariableModifier:
    final
    Annotation

FormalParameterDeclsRest: 
    VariableDeclaratorId [, FormalParameterDecls]
    ... VariableDeclaratorId



VariableDeclaratorId:
    Identifier {[]}



VariableDeclarators:
    VariableDeclarator { , VariableDeclarator }

VariableDeclarator:
    Identifier VariableDeclaratorRest

VariableDeclaratorRest:
    {[]} [ = VariableInitializer ]

VariableInitializer:
    ArrayInitializer
    Expression

ArrayInitializer:
    { [ VariableInitializer { , VariableInitializer } [,] ] }


Block: 
    { BlockStatements }

BlockStatements: 
    { BlockStatement }

BlockStatement:
    LocalVariableDeclarationStatement
    ClassOrInterfaceDeclaration
    [Identifier :] Statement

LocalVariableDeclarationStatement:
    { VariableModifier }  Type VariableDeclarators ;

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

StatementExpression: 
    Expression


Catches:
    CatchClause { CatchClause }

CatchClause:  
    catch ( {VariableModifier} CatchType Identifier ) Block

CatchType:
    QualifiedIdentifier { | QualifiedIdentifier }

Finally:
    finally Block

ResourceSpecification:
    ( Resources [;] )

Resources:
    Resource { ; Resource }

Resource:
    {VariableModifier} ReferenceType VariableDeclaratorId = Expression 


SwitchBlockStatementGroups: 
    { SwitchBlockStatementGroup }

SwitchBlockStatementGroup: 
    SwitchLabels BlockStatements

SwitchLabels:
    SwitchLabel { SwitchLabel }

SwitchLabel: 
    case Expression :
    case EnumConstantName :
    default :

EnumConstantName:
    Identifier



ForControl:
    ForVarControl
    ForInit ; [Expression] ; [ForUpdate]

ForVarControl:
    {VariableModifier} Type VariableDeclaratorId  ForVarControlRest

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
    Type Identifier AnnotationMethodOrConstantRest ;
    ClassDeclaration
    InterfaceDeclaration
    EnumDeclaration  
    AnnotationTypeDeclaration

AnnotationMethodOrConstantRest:
    AnnotationMethodRest
    ConstantDeclaratorsRest  

AnnotationMethodRest:
    ( ) [[]] [default ElementValue]