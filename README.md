# Real Estate Financing
## Weikyr's Property Financing Simulator

Simulador completo de financiamento imobiliário com **polimorfismo**, **herança**, **exceções personalizadas**, **validações de regras de negócio** e **persistência de dados entre execuções**.

Projeto desenvolvido em **Java 21** como entrega final da disciplina de Programação Orientada a Objetos — **todos os requisitos atendidos e superados**.

### Funcionalidades Implementadas

- Três tipos de imóvel com regras específicas:
    - **House** → acréscimo fixo de R$ 80,00 + **regra de negócio**: rejeita se o acréscimo ultrapassar 50% dos juros mensais
    - **Apartment** → cálculo correto com juros compostos (Sistema PRICE)
    - **Plot** → acréscimo de 2% sobre a parcela
- Seleção de 6 imóveis pré-cadastrados com descrição realista
- Entrada robusta com validação completa
- Polimorfismo total no cálculo de parcelas e valores totais
- Exceção personalizada `IncreaseGreaterThanInterestException`
- **Persistência completa entre execuções**:
    - `financings.ser` → arquivo binário (serialização) → carregado automaticamente ao iniciar
    - `financings.txt` → arquivo texto legível → backup e inspeção
- Resumo final detalhado com totais e tratamento de financiamentos rejeitados
- Gerenciamento seguro de recursos com `try-with-resources`

### Nova Regra de Negócio

> O acréscimo fixo de R$ 80,00 em casas **não pode ser maior que 50% do valor dos juros mensais**.  
> Se violar → financiamento rejeitado e não entra nos totais.

### Persistência de Dados

- Ao encerrar → todos os financiamentos aprovados são salvos em **dois formatos**
- Na próxima execução → os dados anteriores são carregados automaticamente do `.ser`
- Você continua exatamente de onde parou

### Estrutura do Projeto
````bash
    src/
    ├── main/
    │   └── Main.java
    ├── model/
    │   ├── Financing.java
    │   ├── House.java                 ← com validação da regra dos R$ 80
    │   ├── Apartment.java             ← Sistema PRICE
    │   ├── Plot.java
    │   └── IncreaseGreaterThanInterestException.java
    └── util/
    ├── UserInterface.java
    ├── InputProvider.java
    └── FinancingPersistence.java  ← persistência texto + binário
````
### Arquivos Gerados
````bash
  financings.ser  ← carregado automaticamente
  financings.txt ← legível (exemplo abaixo)
````

### Tecnologias & Boas Práticas

* Herança / Polimorfismo
* Exceções checked personalizadas
* Serialização (Serializable)
* Persistência em arquivo texto + binário
* Try-with-resources (zero vazamento de recursos
* Código totalmente documentado
* Estrutura de pacotes limpa


                                        🏠🚀📖