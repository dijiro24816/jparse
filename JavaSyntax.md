```
	 -> Identifier
IdentifierPeriodRepeat -> IdentifierPeriodRepeat . Identifier


ImportDeclarationRepeat -> ImportDeclaration
ImportDeclarationRepeat -> ImportDeclarationRepeat ImportDeclaration

TypeDeclarationRepeat -> TypeDeclaration
TypeDeclarationRepeat -> TypeDeclarationRepeat TypeDeclaration

IdentifierAsteriskPeriodRepeat -> Identifier
IdentifierAsteriskPeriodRepeat -> IdentifierAsteriskPeriodRepeat . Identifier
IdentifierAsteriskPeriodRepeat -> IdentifierAsteriskPeriodRepeat . *

ModifierRepeat -> Modifier
ModifierRepeat -> ModifierRepeat Modifier

IdentifierTypeArgumentPeriodRepeat -> Identifier
IdentifierTypeArgumentPeriodRepeat -> Identifier TypeArguments
IdentifierTypeArgumentPeriodRepeat -> IdentifierTypeArgumentPeriodRepeat . Identifier
IdentifierTypeArgumentPeriodRepeat -> IdentifierTypeArgumentPeriodRepeat . Identifier TypeArguments


EmptySquareBrackets -> [ ]
EmptySquareBrackets -> EmptySquareBrackets [ ]

TypeArgument_CommaRepeat -> TypeArgument
TypeArgument_CommaRepeat -> TypeArgument_CommaRepeat , TypeArgument


ReferenceTypeCommaRepeat -> ReferenceType
ReferenceTypeCommaRepeat -> ReferenceTypeCommaRepeat , ReferenceType

TypeParameterCommaRepeat -> TypeParameter
TypeParameterCommaRepeat -> TypeParameterCommaRepeat , TypeParameter

ReferenceTypeAndRepeat -> ReferenceType
ReferenceTypeAndRepeat -> ReferenceTypeAndRepeat & ReferenceType


AnnotationRepeat -> Annotation
AnnotationRepeat -> AnnotationRepeat Annotation

ElementValuePairCommaRepeat -> ElementValuePair
ElementValuePairCommaRepeat -> ElementValuePairCommaRepeat , ElementValuePair

ElementValuesComma_Repeat -> 
ElementValuesComma_Repeat -> ElementValues
ElementValuesComma_Repeat -> ,
ElementValuesComma_Repeat -> ElementValues ,
ElementValuesComma_Repeat -> ElementValuesComma_Repeat ElementValues
ElementValuesComma_Repeat -> ElementValuesComma_Repeat ,
ElementValuesComma_Repeat -> ElementValuesComma_Repeat ElementValues ,

ElementValue_CommaRepeat -> ElementValue
ElementValue_CommaRepeat -> ElementValue_CommaRepeat , ElementValue

```


Identifier:
    IDENTIFIER
    
```
Identifier -> IDENTIFIER
```

QualifiedIdentifier:
    Identifier { . Identifier }
    
```
QualifiedIdentifier -> IdentifierPeriodRepeat
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

CompilationUnit -> ImportDeclarationRepeat
CompilationUnit -> package QualifiedIdentifier ; ImportDeclarationRepeat
CompilationUnit -> Annotations package QualifiedIdentifier ; ImportDeclarationRepeat

CompilationUnit -> TypeDeclarationRepeat
CompilationUnit -> package QualifiedIdentifier ; TypeDeclarationRepeat
CompilationUnit -> Annotations package QualifiedIdentifier ; TypeDeclarationRepeat

CompilationUnit -> ImportDeclarationRepeat TypeDeclarationRepeat
CompilationUnit -> package QualifiedIdentifier ; ImportDeclarationRepeat TypeDeclarationRepeat
CompilationUnit -> Annotations package QualifiedIdentifier ; ImportDeclarationRepeat TypeDeclarationRepeat
```

ImportDeclaration: 
    import [static] Identifier { . Identifier } [. *] ;

```
ImportDeclaration -> import IdentifierPeriodRepeat ;
ImportDeclaration -> import static IdentifierPeriodRepeat ;
ImportDeclaration -> import IdentifierPeriodRepeat . * ;
ImportDeclaration -> import static IdentifierPeriodRepeat . * ;
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
ClassOrInterfaceDeclaration -> ModifierRepeat ClassDeclaration
ClassOrInterfaceDeclaration -> ModifierRepeat InterfaceDeclaration
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
Type -> BasicType EmptySquareBrackets
Type -> ReferenceType
Type -> ReferenceType EmptySquareBrackets
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
ReferenceType -> IdentifierTypeArgumentPeriodRepeat
```



TypeArguments: 
    < TypeArgument { , TypeArgument } >
    
```
TypeArguments -> < TypeArgument_CommaRepeat >
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
TypeList -> ReferenceTypeCommaRepeat
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
TypeParameters -> < TypeParameterCommaRepeat >
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
Bound -> ReferenceTypeAndRepeat
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
Annotations -> AnnotationRepeat
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
ElementValuePairz -> ElementValuePairCommaRepeat
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

ClassBody: 
    { { ClassBodyDeclaration } }

ClassBodyDeclaration:
    ; 
    {Modifier} MemberDecl
    [static] Block

MemberDecl:
    MethodOrFieldDecl
    void Identifier VoidMethodDeclaratorRest
    Identifier ConstructorDeclaratorRest
    GenericMethodOrConstructorDecl
    ClassDeclaration
    InterfaceDeclaration

MethodOrFieldDecl:
    Type Identifier MethodOrFieldRest

MethodOrFieldRest:  
    FieldDeclaratorsRest ;
    MethodDeclaratorRest

FieldDeclaratorsRest:  
    VariableDeclaratorRest { , VariableDeclarator }

MethodDeclaratorRest:
    FormalParameters {[]} [throws QualifiedIdentifierList] (Block | ;)

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