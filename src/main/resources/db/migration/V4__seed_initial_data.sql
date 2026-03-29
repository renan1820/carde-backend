-- ─────────────────────────────────────────────────────────────────────────────
-- VEÍCULOS
-- Dados idênticos ao mock Flutter (mock_vehicles.dart)
-- image_url usa URLs Unsplash como placeholder até o CDN R2 estar configurado
-- ─────────────────────────────────────────────────────────────────────────────

INSERT INTO vehicles (id, name, brand, year, category, short_description, full_history, image_url, engine_sound_url, display_order) VALUES
('v001', 'Ford Model T', 'Ford', 1908, 'classic',
 'O carro que democratizou o automóvel no mundo.',
 'O Ford Model T, apelidado de "Tin Lizzie", foi produzido de 1908 a 1927 e revolucionou a indústria automobilística. Henry Ford introduziu a linha de montagem em 1913, permitindo produzir um carro a cada 93 minutos. Com mais de 15 milhões de unidades fabricadas, o Model T tornou o automóvel acessível à classe média americana e mudou para sempre a mobilidade urbana global. Sua chegada ao Brasil, nos anos 1920, transformou as cidades e impulsionou a construção de rodovias.',
 'https://images.unsplash.com/photo-1494976388531-d1058494cdd8?w=800&auto=format&fit=crop&q=80',
 NULL, 0),

('v002', 'Volkswagen Fusca', 'Volkswagen', 1959, 'classic',
 'O carro do povo que conquistou o coração dos brasileiros.',
 'O Volkswagen Fusca — Beetle no exterior — chegou ao Brasil em 1950 e foi produzido localmente a partir de 1959. Tornou-se o símbolo da motorização popular brasileira, sendo vendido até 1996. Com design criado por Ferdinand Porsche, o Fusca tinha motor traseiro refrigerado a ar e era conhecido pela durabilidade e baixo custo de manutenção. No auge, representava mais de 50% dos automóveis em circulação no país. Em 1993, ganhou versão reestilizada que foi produzida até 1996.',
 'https://images.unsplash.com/photo-1583121274602-3e2820c69888?w=800&auto=format&fit=crop&q=80',
 NULL, 1),

('v003', 'Chevrolet Opala', 'Chevrolet', 1969, 'car',
 'O muscle car brasileiro, símbolo de potência e estilo.',
 'O Chevrolet Opala foi lançado no Brasil em 1968 e produzido até 1992, tornando-se o automóvel de alto padrão mais popular do país por décadas. Com linhas americanas imponentes, o Opala conquistou políticos, empresários e entusiastas. Sua versão Comodoro 6 cilindros era sinônimo de status. O Opala foi o primeiro carro brasileiro a receber motor seis cilindros em linha e, em sua última geração, chegou com o motor 4.1 litros de 150 cavalos. Hoje é um dos veículos mais cobiçados pelos colecionadores brasileiros.',
 'https://images.unsplash.com/photo-1552519507-da3b142c6e3d?w=800&auto=format&fit=crop&q=80',
 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3', 2),

('v004', 'Puma GTE', 'Puma', 1974, 'racing',
 'O esportivo nacional que rivaliza com os melhores do mundo.',
 'A Puma Veículos e Motores nasceu em 1964 no Brasil e criou alguns dos esportivos mais belos já produzidos em solo nacional. O modelo GTE, lançado em 1974, tinha carroceria em fibra de vidro e motor DKW bicilíndrico de dois tempos, evoluindo posteriormente para motores VW. O Puma representou o sonho brasileiro de ter um carro esportivo genuinamente nacional, competindo em rallies e corridas por todo o país. Seu design inspirado nos GTs italianos conquistou admiradores no mundo inteiro.',
 'https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=800&auto=format&fit=crop&q=80',
 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3', 3),

('v005', 'Ford Maverick', 'Ford', 1973, 'car',
 'O compacto americano que virou ícone nas ruas brasileiras.',
 'O Ford Maverick brasileiro foi produzido de 1973 a 1979 e tornou-se um fenômeno de vendas. Ao contrário do Maverick americano, a versão nacional ganhou motor VW e depois motor Ford local, o que o tornou uma mescla única de influências. Com linhas esportivas e baixo custo de manutenção, o Maverick era o sonho de jovens da classe média. Participou de inúmeras competições nacionais e é hoje um dos carros clássicos mais valorizados do mercado brasileiro, com exemplares chegando a R$ 200 mil.',
 'https://images.unsplash.com/photo-1546549990-3c1e0e7d1a17?w=800&auto=format&fit=crop&q=80',
 NULL, 4),

('v006', 'Honda CB 500', 'Honda', 1971, 'motorcycle',
 'A motocicleta que redefiniu a performance nas duas rodas.',
 'A Honda CB 500 Four chegou em 1971 e revolucionou o segmento de motocicletas esportivas. Com seu motor quatro cilindros em linha e escapamento 4-em-1, oferecia uma sonoridade e suavidade inéditas. No Brasil, as motos Honda transformaram o mercado de duas rodas a partir dos anos 1970, com a fábrica em Manaus inaugurada em 1971. A CB 500 se tornou referência de engenharia para toda a indústria e influenciou gerações de motocicletas até hoje.',
 'https://images.unsplash.com/photo-1558618047-3c8c76ca7d13?w=800&auto=format&fit=crop&q=80',
 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-11.mp3', 5),

('v007', 'Rolls-Royce Silver Ghost', 'Rolls-Royce', 1910, 'classic',
 'O melhor carro do mundo, segundo o próprio fabricante.',
 'O Rolls-Royce Silver Ghost é considerado um dos maiores automóveis já construídos. Produzido de 1906 a 1926, o Ghost recebeu o título de "o melhor carro do mundo" após completar 14.371 milhas sem falhas mecânicas em 1907. O motor de 7 litros funcionava com tanto silêncio que o ruído mais alto era o do relógio no painel. A unidade do acervo CARDE percorreu as estradas da Europa e chegou ao Brasil em 1915, sendo propriedade de um cafeicultor paulista por mais de 40 anos.',
 'https://images.unsplash.com/photo-1563720223809-b2a9a1855d3f?w=800&auto=format&fit=crop&q=80',
 NULL, 6),

('v008', 'Willys Jeep', 'Willys', 1944, 'car',
 'O veículo que abriu o Brasil com suas rodas.',
 'O Jeep Willys foi fundamental para a Segunda Guerra Mundial e, depois, para o desenvolvimento do interior brasileiro. A Willys-Overland do Brasil iniciou a produção nacional em 1954, no Rio de Janeiro. O Jeep foi essencial na abertura de estradas, construção de Brasília e exploração do Cerrado e Amazônia. Resistente, simples e versátil, tornou-se o veículo preferido de fazendeiros, militares e aventureiros. Esta unidade do acervo participou da abertura da rodovia Belém-Brasília nos anos 1960.',
 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=800&auto=format&fit=crop&q=80',
 NULL, 7),

('v009', 'Lancia Stratos', 'Lancia', 1975, 'racing',
 'Tricampeão mundial de rally, o mais radical de todos.',
 'O Lancia Stratos HF é considerado o primeiro carro projetado exclusivamente para o rally. Vencedor dos campeonatos mundiais de 1974, 1975 e 1976, o Stratos era uma máquina radical com carroceria em fibra de vidro e motor Ferrari Dino V6 de 190 cv. Apenas 492 unidades foram produzidas. O exemplar do acervo CARDE disputou o Rally dos Pampas no Brasil em 1977 e é um dos três Stratos conhecidos em solo nacional, restaurado em 2018 para seu estado original de competição.',
 'https://images.unsplash.com/photo-1504215680853-026ed2a45def?w=800&auto=format&fit=crop&q=80',
 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-15.mp3', 8),

('v010', 'FNM JK', 'FNM', 1960, 'car',
 'O carro de Juscelino, símbolo do Brasil moderno.',
 'A FNM (Fábrica Nacional de Motores) produziu o JK de 1960 a 1968, sendo o primeiro sedan de luxo fabricado inteiramente no Brasil. Baseado no Alfa Romeo 2000, o JK homenageava o presidente Juscelino Kubitschek, que acelerou a industrialização automobilística nacional. Com motor 2.0 de 90 cavalos e acabamento premium, era considerado o automóvel mais sofisticado produzido no Brasil. A FNM foi a pioneira na fabricação de automóveis com motor de dois litros no país, influenciando toda a indústria nacional.',
 'https://images.unsplash.com/photo-1541899481282-d53bffe3c35d?w=800&auto=format&fit=crop&q=80',
 NULL, 9);

-- ─────────────────────────────────────────────────────────────────────────────
-- SPECS DOS VEÍCULOS (mesma ordem do mock Flutter)
-- ─────────────────────────────────────────────────────────────────────────────

INSERT INTO vehicle_specs (vehicle_id, spec_key, spec_value, sort_order) VALUES
-- v001 Ford Model T
('v001', 'Motor',           '2.9L 4 cilindros', 0),
('v001', 'Potência',        '20 hp',            1),
('v001', 'Velocidade máx.', '72 km/h',          2),
('v001', 'Peso',            '540 kg',           3),
('v001', 'Combustível',     'Gasolina',         4),

-- v002 Volkswagen Fusca
('v002', 'Motor',           '1.3L ar refrigerado', 0),
('v002', 'Potência',        '34 hp',               1),
('v002', 'Velocidade máx.', '115 km/h',            2),
('v002', 'Peso',            '780 kg',              3),
('v002', 'Produção Brasil', '1959–1996',           4),

-- v003 Chevrolet Opala
('v003', 'Motor',           '4.1L 6 cilindros', 0),
('v003', 'Potência',        '150 hp',           1),
('v003', 'Velocidade máx.', '175 km/h',         2),
('v003', 'Peso',            '1.380 kg',         3),
('v003', 'Produção Brasil', '1968–1992',        4),

-- v004 Puma GTE
('v004', 'Motor',           '1.6L VW',         0),
('v004', 'Potência',        '65 hp',           1),
('v004', 'Velocidade máx.', '170 km/h',        2),
('v004', 'Carroceria',      'Fibra de vidro',  3),
('v004', 'Peso',            '780 kg',          4),

-- v005 Ford Maverick
('v005', 'Motor',           '2.3L 4 cilindros', 0),
('v005', 'Potência',        '84 hp',            1),
('v005', 'Velocidade máx.', '155 km/h',         2),
('v005', 'Peso',            '1.020 kg',         3),
('v005', 'Produção Brasil', '1973–1979',        4),

-- v006 Honda CB 500
('v006', 'Motor',           '498cc 4 cilindros', 0),
('v006', 'Potência',        '50 hp',             1),
('v006', 'Velocidade máx.', '180 km/h',          2),
('v006', 'Peso',            '187 kg',            3),
('v006', 'Câmbio',          '5 marchas',         4),

-- v007 Rolls-Royce Silver Ghost
('v007', 'Motor',           '7.4L 6 cilindros', 0),
('v007', 'Potência',        '48 hp',            1),
('v007', 'Velocidade máx.', '105 km/h',         2),
('v007', 'Peso',            '1.500 kg',         3),
('v007', 'Produção',        '1906–1926',        4),

-- v008 Willys Jeep
('v008', 'Motor',           '2.2L 4 cilindros', 0),
('v008', 'Potência',        '60 hp',            1),
('v008', 'Tração',          '4x4',              2),
('v008', 'Peso',            '1.100 kg',         3),
('v008', 'Produção Brasil', '1954–1983',        4),

-- v009 Lancia Stratos
('v009', 'Motor',               '2.4L V6 Ferrari',                    0),
('v009', 'Potência',            '190 hp (estrada) / 280 hp (rally)',  1),
('v009', 'Velocidade máx.',     '232 km/h',                           2),
('v009', 'Peso',                '870 kg',                             3),
('v009', 'Unidades produzidas', '492',                                4),

-- v010 FNM JK
('v010', 'Motor',           '2.0L 4 cilindros Alfa Romeo', 0),
('v010', 'Potência',        '90 hp',                       1),
('v010', 'Velocidade máx.', '165 km/h',                    2),
('v010', 'Peso',            '1.160 kg',                    3),
('v010', 'Produção',        '1960–1968',                   4);

-- ─────────────────────────────────────────────────────────────────────────────
-- EVENTOS (dados idênticos ao mock Flutter — mock_events.dart)
-- ─────────────────────────────────────────────────────────────────────────────

INSERT INTO museum_events (id, title, description, event_date, image_url, is_featured) VALUES
('e001',
 'Night Drive: Clássicos sob as Estrelas',
 'Uma noite especial com os veículos mais icônicos do acervo iluminados ao luar. Visita guiada com curador.',
 '2025-04-19T00:00:00-03:00',
 'https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=800&auto=format&fit=crop&q=80',
 TRUE),

('e002',
 'Oficina: Restauração de Clássicos',
 'Workshop prático com os mestres restauradores do CARDE. Aprenda técnicas de pintura e mecânica vintage.',
 '2025-04-26T00:00:00-03:00',
 'https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=800&auto=format&fit=crop&q=80',
 FALSE),

('e003',
 'Exposição Temporária: Formula Brasil',
 'Os carros que marcaram a história do automobilismo nacional, da Fórmula Ford à Stock Car.',
 '2025-05-03T00:00:00-03:00',
 'https://images.unsplash.com/photo-1541805625-3de1346dc6f3?w=800&auto=format&fit=crop&q=80',
 TRUE),

('e004',
 'Encontro de Motocicletas Clássicas',
 'Proprietários de motos históricas expõem suas máquinas nos jardins do museu. Entrada gratuita para participantes.',
 '2025-05-10T00:00:00-03:00',
 'https://images.unsplash.com/photo-1449426468159-d96dbf08f19f?w=800&auto=format&fit=crop&q=80',
 FALSE);
