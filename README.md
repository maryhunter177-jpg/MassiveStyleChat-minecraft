# DeorumChat 💬

Plugin profissional de chat para Minecraft desenvolvido sob medida, com suporte a Markdown, cores e sistema de canais independentes.

## ✨ Funcionalidades
* **Sistema de Canais**: Chat Global (`/g`) e Local (`/l`) já configurados.
* **Formatação Rica**: Suporte a cores e formatação estilo Markdown.
* **Zero Conflitos**: Desenvolvido para substituir plugins pesados de chat.
* **Alta Performance**: Otimizado para não causar lag no servidor.
* **Configuração Fácil**: Arquivo `channels.yml` para criar novos canais ou mudar prefixos.

---

## 🚀 Como Instalar (Passo a Passo)

1. **Baixe o Plugin**:
   * Vá até a pasta `target` deste repositório.
   * Clique no arquivo **`DeorumChat-1.0-SNAPSHOT.jar`** e faça o download.

2. **Prepare o Servidor**:
   * **Importante**: Remova ou desative o plugin *VentureChat* (ou outros de chat) para evitar mensagens duplicadas.
   * Acesse a pasta `plugins` do seu servidor.

3. **Instalação**:
   * Arraste o arquivo `DeorumChat-1.0-SNAPSHOT.jar` para dentro da pasta `plugins`.
   * Reinicie o servidor ou digite `/reload confirm`.

4. **Verificação**:
   * Digite `/plugins` no jogo. Se **DeorumChat** estiver verde, está funcionando!

---

## 🛠️ Comandos e Permissões

### Atalhos Rápidos
* `/g <mensagem>` - Fala no Chat Global (para todo o servidor).
* `/l <mensagem>` - Fala no Chat Local (apenas para quem está perto).

### Administração
* `/chat reload` - Recarrega as configurações caso você mude as cores ou canais.
  * **Permissão**: `deorumchat.admin` (Padrão para OPs).

---

## ⚙️ Como Configurar Canais
Após rodar o plugin pela primeira vez, será criado um arquivo chamado `channels.yml`. Você pode editá-lo para mudar as cores e prefixos:

```yaml
channels:
  global:
    prefix: "&b[Global] &r"   
    range: -1                 
  local:
    prefix: "&e[Local] &r"    
    range: 100                