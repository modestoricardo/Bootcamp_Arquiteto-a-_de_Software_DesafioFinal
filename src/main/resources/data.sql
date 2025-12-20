-- ===================================================================
-- DADOS INICIAIS PARA TESTES
-- API REST de Produtos - Bootcamp Arquiteto de Software
-- ===================================================================

-- Inserindo produtos de Informática
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria, data_cadastro) VALUES
('Notebook Dell Inspiron 15', 'Notebook Dell Inspiron 15, Intel Core i5, 8GB RAM, 256GB SSD', 3500.00, 15, 'Informática', CURRENT_TIMESTAMP()),
('Mouse Logitech MX Master 3', 'Mouse sem fio Logitech MX Master 3, ergonômico, sensor de alta precisão', 350.00, 50, 'Periféricos', CURRENT_TIMESTAMP()),
('Teclado Mecânico Keychron K2', 'Teclado mecânico sem fio Keychron K2, switches Gateron Blue', 450.00, 30, 'Periféricos', CURRENT_TIMESTAMP()),
('Monitor LG UltraWide 29"', 'Monitor LG 29 polegadas UltraWide, resolução 2560x1080, IPS', 1200.00, 20, 'Monitores', CURRENT_TIMESTAMP()),
('Webcam Logitech C920', 'Webcam Full HD 1080p, microfone estéreo integrado', 450.00, 40, 'Periféricos', CURRENT_TIMESTAMP());

-- Inserindo produtos de Celulares e Tablets
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria, data_cadastro) VALUES
('iPhone 15 Pro Max 256GB', 'Apple iPhone 15 Pro Max, 256GB, câmera tripla, chip A17 Pro', 8500.00, 10, 'Celulares', CURRENT_TIMESTAMP()),
('Samsung Galaxy S24 Ultra', 'Samsung Galaxy S24 Ultra, 512GB, câmera de 200MP, S Pen incluída', 7500.00, 12, 'Celulares', CURRENT_TIMESTAMP()),
('iPad Air 11" 128GB', 'Apple iPad Air com tela de 11 polegadas, chip M2, 128GB', 4500.00, 18, 'Tablets', CURRENT_TIMESTAMP()),
('Xiaomi Redmi Note 13 Pro', 'Xiaomi Redmi Note 13 Pro, 256GB, câmera de 108MP, carregamento rápido 67W', 2200.00, 35, 'Celulares', CURRENT_TIMESTAMP());

-- Inserindo produtos de Áudio
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria, data_cadastro) VALUES
('Fone Sony WH-1000XM5', 'Fone de ouvido Sony com cancelamento de ruído premium, Bluetooth 5.2', 1800.00, 25, 'Áudio', CURRENT_TIMESTAMP()),
('Caixa de Som JBL Flip 6', 'Caixa de som portátil JBL Flip 6, à prova d''água, 12 horas de bateria', 650.00, 45, 'Áudio', CURRENT_TIMESTAMP()),
('AirPods Pro 2ª Geração', 'Apple AirPods Pro com cancelamento ativo de ruído, case MagSafe', 1900.00, 30, 'Áudio', CURRENT_TIMESTAMP());

-- Inserindo produtos de Games
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria, data_cadastro) VALUES
('PlayStation 5 Digital', 'Console Sony PlayStation 5 versão Digital, 825GB SSD', 3800.00, 8, 'Games', CURRENT_TIMESTAMP()),
('Xbox Series S 512GB', 'Console Microsoft Xbox Series S, 512GB SSD, suporte a 1440p', 2500.00, 15, 'Games', CURRENT_TIMESTAMP()),
('Nintendo Switch OLED', 'Console Nintendo Switch modelo OLED, tela de 7 polegadas', 2300.00, 20, 'Games', CURRENT_TIMESTAMP()),
('Controle Xbox Elite Series 2', 'Controle Xbox Elite Series 2, componentes intercambiáveis, case de transporte', 1200.00, 12, 'Games', CURRENT_TIMESTAMP());

-- Inserindo produtos de Smart Home
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria, data_cadastro) VALUES
('Echo Dot 5ª Geração', 'Amazon Echo Dot com Alexa, controle de casa inteligente por voz', 350.00, 60, 'Smart Home', CURRENT_TIMESTAMP()),
('Google Nest Mini', 'Alto-falante inteligente Google Nest Mini com Google Assistente', 280.00, 55, 'Smart Home', CURRENT_TIMESTAMP()),
('Lâmpada Inteligente Philips Hue', 'Lâmpada LED inteligente Philips Hue, 16 milhões de cores, WiFi', 180.00, 80, 'Smart Home', CURRENT_TIMESTAMP()),
('Ring Video Doorbell Pro', 'Campainha inteligente Ring com vídeo HD, visão noturna, detecção de movimento', 850.00, 25, 'Smart Home', CURRENT_TIMESTAMP());

-- Inserindo produtos de Fotografia
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria, data_cadastro) VALUES
('Canon EOS R6 Mark II', 'Câmera mirrorless Canon EOS R6 Mark II, sensor full-frame de 24MP', 15000.00, 5, 'Câmeras', CURRENT_TIMESTAMP()),
('GoPro Hero 12 Black', 'Action camera GoPro Hero 12 Black, gravação 5.3K, estabilização HyperSmooth', 2800.00, 18, 'Câmeras', CURRENT_TIMESTAMP()),
('DJI Mini 3 Pro', 'Drone DJI Mini 3 Pro, câmera 4K, tempo de voo de 34 minutos', 4500.00, 10, 'Drones', CURRENT_TIMESTAMP());

-- Total: 25 produtos cadastrados em diversas categorias
