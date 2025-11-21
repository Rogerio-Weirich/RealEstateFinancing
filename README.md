# Real Estate Financing
## Weikyr's Property Financing Simulator

Simulador de financiamento imobiliário com polimorfismo, herança, exceções personalizadas e validações de regras de negócio.

Projeto desenvolvido em Java 21 como parte da disciplina de Programação Orientada a Objetos.

## Funcionalidades Implementadas

- Três tipos de imóvel com regras específicas:
    - **House** → acréscimo fixo de R$ 80,00 na parcela
    - **Apartment** → cálculo correto com juros compostos (Sistema PRICE)
    - **Plot** → acréscimo de 2% se for zona comercial
- Seleção de 6 imóveis pré-cadastrados
- Validação robusta de entrada de dados
- Polimorfismo completo no cálculo de parcelas e totais
- **Nova regra de negócio (implementada nesta versão):**
  > **O acréscimo fixo de R$ 80,00 em casas não pode ser maior que 50% do valor dos juros mensais.**
  > Caso viole essa regra, o financiamento é rejeitado com mensagem clara e não entra nos totais.

## Nova Exceção Personalizada
model/IncreaseGreaterThanInterestException.java

Exceção checked personalizada lançada quando o acréscimo de R$ 80,00 na House for desproporcional em relação aos juros mensais.
## Depuração com VS Code (Java 21)

Notas rápidas para executar e depurar este projeto no VS Code usando Java 21.

1. Certifique-se de que o Java 21 está instalado e que a variável de ambiente `JAVA_HOME` aponta para ele.

2. Reinicie/reabra o VS Code após qualquer alteração no ambiente para que as extensões Java detectem o JDK corretamente.

3. Limpe o workspace do Java Language Server (caso apareçam erros estranhos):
    - Pressione `Ctrl+Shift+P` → **Java: Clean Java Language Server Workspace** → escolha **Restart and delete**.

4. Use as configurações de depuração já fornecidas (aba Run):
    - **Main** e **Debug Financing** estão configuradas para rodar no **Integrated Terminal** (terminal integrado) para que a leitura de entrada (`stdin`) e a avaliação funcionem de forma confiável.
    - Se preferir o Debug Console, edite o arquivo `.vscode/launch.json` e remova ou altere a linha `"console": "integratedTerminal"`.

5. Caso ainda veja a mensagem `unrecognized request: {_request: evaluate}` no Debug Console, prefira o terminal integrado (as configurações de debug já fazem isso) ou atualize as extensões Java (Language Support for Java by Red Hat e Debugger for Java by Microsoft).

6. Para compilar e executar rapidamente pelo terminal:

```bash
     # compile (incluindo todas as classes)
javac -d out src/**/*.java

# run
java -cp out main.Main
Estrutura do Projeto
textsrc/
├── main/
│   └── Main.java                  ← programa principal
├── model/
│   ├── Financing.java             ← classe abstrata
│   ├── House.java                 ← + validação da regra dos R$ 80
│   ├── Apartment.java
│   ├── Plot.java
│   └── IncreaseGreaterThanInterestException.java  ← nova exceção
└── util/
    └── UserInterface.java         ← interface com o usuário (validação robusta)
Exemplo de Saída com Regra Violada (House rejeitada)
textValidation Error (House): The fixed increase of R$ 80.00 exceeds half of the monthly interest amount (half interest: R$ 166.67). This House financing is not permitted.

Monthly Payment: FINANCING REJECTED
Reason: R$ 80.00 increase exceeds half of monthly interest.
Total paid (interest): NOT CALCULATED
Financiamentos rejeitados não são somados nos totais finais.
Como Testar a Nova Regra

Escolha uma House (opções 3 ou 4)
Use taxa de juros baixa (ex: 0.5% a 1.0%) e prazo longo
→ O financiamento será rejeitado
Use taxa normal (ex: 8.5%)
→ Financiamento aceito normalmente com +R$ 80

🏠📖🚀