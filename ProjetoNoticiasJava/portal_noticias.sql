-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 30-Nov-2022 às 16:24
-- Versão do servidor: 10.4.25-MariaDB
-- versão do PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `portal_noticias`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categorias`
--

CREATE TABLE `categorias` (
  `idCategoria` int(11) NOT NULL,
  `nome` varchar(120) NOT NULL,
  `descricao` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `categorias`
--

INSERT INTO `categorias` (`idCategoria`, `nome`, `descricao`) VALUES
(1, 'Esportes', 'Descrição de esportes super pequena e resumida'),
(2, 'Política', 'Não fale sobre isto em família ou igreja.'),
(4, 'Tecnologia', 'Assuntos relacionados as tecnologias do mundo'),
(5, 'Saúde', 'Notícias sobre os estudos para o bem-estar do corpo e da saúde'),
(6, 'Estilo', 'Artigos sobre roupas e acessórios'),
(7, 'Ciência', 'Ciência refere-se a qualquer conhecimento ou prática sistemáticos. Em sentido estrito, ciência refere-se ao sistema de adquirir conhecimento baseado no método científico'),
(8, 'Design', 'Tipografia moderna, chamada também de design, é a idealização, criação, desenvolvimento, configuração, concepção, elaboração e especificação de produtos.'),
(9, 'Cultura', 'Cultura é um conceito de várias acepções, sendo a mais corrente, especialmente na antropologia, a definição genérica formulada por Edward B.'),
(10, 'Negócios', 'Em economia, negócio é referido como um comércio ou empresa que é administrado por pessoa para captar recursos financeiros a fim de gerar bens e serviços'),
(11, 'Viagem', 'Dicas e promoções para fazer sua viagem dos sonhos!'),
(12, 'Mundo', 'Notícias ao redor do mundo muito loucas');

-- --------------------------------------------------------

--
-- Estrutura da tabela `noticias`
--

CREATE TABLE `noticias` (
  `id` int(11) NOT NULL,
  `autor` varchar(120) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `descricao` varchar(300) NOT NULL,
  `texto` text NOT NULL,
  `idCategoria` int(11) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `updatedAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deletedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `noticias`
--

INSERT INTO `noticias` (`id`, `autor`, `titulo`, `descricao`, `texto`, `idCategoria`, `createdAt`, `updatedAt`, `deletedAt`) VALUES
(1, 'Um autor brabo', 'Um titulo criativo', 'Descrição sucinta sobre a notícia', 'egregrege', 2, '2022-11-10 00:28:34', '2022-11-28 01:23:33', NULL),
(2, 'Autor desconhecido', 'Outra notícia criativa', 'Isso é uma descrição genial com um titulo criativo', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 1, '2022-11-10 00:29:50', '2022-11-30 02:14:00', NULL),
(3, 'Nicolas Morche', 'Uma notícia a ser excluida', 'Uma breve introdução sobre uma notícia a ser excluída', 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).', 2, '2022-11-28 00:03:31', '2022-11-28 00:10:10', '2022-11-28 00:10:10'),
(4, 'Autor esperto', 'Uma notícia a ser excluida', 'Descrição sucinta sobre a notícia', 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).', 1, '2022-11-28 00:15:30', '2022-11-28 00:17:23', '2022-11-28 00:17:23'),
(5, 'Autor desconhecido', 'Outra notícia criativa teste', 'Isso é uma descrição genial com um titulo criativo', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 1, '2022-11-30 01:35:03', '2022-11-30 01:46:50', '2022-11-30 01:46:50'),
(6, 'gergae', 'fbdhdrtgnjdrfgjndrf', 'bdsftnjsrtgnjsr', 'njgfsrnjsdrgftntgdrf', 7, '2022-11-30 02:08:41', '2022-11-30 02:17:13', '2022-11-30 02:17:13'),
(7, 'Autor desconhecido', 'Outra notícia criativa 43534', 'Isso é uma descrição genial com um titulo criativo', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 1, '2022-11-30 02:09:34', '2022-11-30 02:11:58', '2022-11-30 02:11:58'),
(8, 'Autor desconhecido', 'Outra notícia criativa 4546w456', 'Isso é uma descrição genial com um titulo criativo', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 1, '2022-11-30 02:09:57', '2022-11-30 02:11:56', '2022-11-30 02:11:56'),
(9, 'nsgrfnsxgrnjsgxdrnj', 'sdbgrfdhbndsn', 'gnjxdgfrnjxgdrfjnsgxrjn', 'rgjnsrgjnsrgjnsr', 1, '2022-11-30 02:13:54', '2022-11-30 02:14:16', '2022-11-30 02:14:16'),
(10, 'Nicolas Morche', 'Elon Musk maior influenciador do Twitter: como crescimento do executivo afeta a rede social', 'A indústria das mídias sociais nunca teve uma plataforma em que seu principal executivo é também o mais popular na rede. O que isso pode significar para o empresário e para a empresa?', 'Terça-feira, dia 17 de janeiro de 2023. Esse é o dia em que Elon Musk se tornará o influenciador número um do Twitter, segundo previsões de analistas.\r\n\r\nCom 120 milhões de seguidores, a conta @ElonMusk já é a segunda mais seguida das redes sociais, atrás apenas de @BarackObama, do ex-presidente dos Estados Unidos, que tem 130 milhões.\r\n\r\nSegundo estatísticos da Social Blade, uma plataforma que reúne métricas de redes sociais, após um aumento meteórico em sua popularidade desde que comprou o Twitter, Musk inevitavelmente ultrapassará os números de Obama.\r\n\r\nA indústria das mídias sociais nunca teve uma plataforma em que seu principal executivo é também o mais popular na rede. O que isso pode mudar para ele e para a rede social?', 4, '2022-11-30 02:39:29', '2022-11-30 02:39:29', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `usuario` varchar(150) NOT NULL,
  `senha` varchar(20) NOT NULL DEFAULT 'portal123',
  `nome` varchar(300) NOT NULL,
  `email` varchar(180) DEFAULT NULL,
  `nivelPermissao` int(1) NOT NULL DEFAULT 1,
  `ativo` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `usuario`, `senha`, `nome`, `email`, `nivelPermissao`, `ativo`) VALUES
(1, 'nicolas.morche', '123456', 'Nicolas Lima Morche', 'naosei@naosei.com.br', 1, 1),
(2, 'admin', '123456', 'Admin', 'admin@admin.com.br', 2, 1),
(3, 'edgar.poe', 'portal123', 'Edgar Alan Poe Teste', 'edgar.poe@rip.com.br', 1, 0),
(5, 'lovecraft', 'portal123', 'Lovecraft', 'lovecraft@lovecraft.com.br', 1, 0),
(7, 'lewis', 'portal123', 'C. s. Lewis', 'lewis@lewis.com.br', 1, 0);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Índices para tabela `noticias`
--
ALTER TABLE `noticias`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `categorias`
--
ALTER TABLE `categorias`
  MODIFY `idCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `noticias`
--
ALTER TABLE `noticias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
