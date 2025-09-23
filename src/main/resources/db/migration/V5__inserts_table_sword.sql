ALTER TABLE sword
    ALTER COLUMN RARITY TYPE VARCHAR(255);

INSERT INTO sword (
    name, rarity, damage, characteristics, description, owner_id, image_url, price
) VALUES (
             'Lâmina de Andrômeda',
             'GALATICO',
             95.0,
             ARRAY['Corte dimensional', 'Brilho estelar', 'Forjada com matéria escura'],
             'Uma espada lendária que canaliza o poder da galáxia de Andrômeda. Dizem que seu corte pode atravessar o tecido do espaço-tempo.',
             NULL,
             '/public/static/img/andromeda_sword.png',
             12000.00
         );

INSERT INTO sword (
    name, rarity, damage, characteristics, description, owner_id, image_url, price
) VALUES (
             'Espada de Zarkon',
             'MITICO',
             88.5,
             ARRAY['Energia alienígena', 'Resistência a plasma', 'Empunhada por caçadores de estrelas'],
             'Criada por uma civilização extinta do sistema Zarkon, essa espada vibra com energia alienígena pura.',
             NULL,
             '/public/static/img/zarkon_blade.png',
             9800.00
         );

INSERT INTO sword (
    name, rarity, damage, characteristics, description, owner_id, image_url, price
) VALUES (
             'Fenda Cósmica',
             'EPICO',
             76.0,
             ARRAY['Corte gravitacional', 'Efeito de distorção', 'Atrai meteoritos pequenos'],
             'Uma arma épica que manipula a gravidade ao seu redor. Ideal para combates em ambientes de baixa gravidade.',
             NULL,
             '/public/static/img/cosmic_rift.png',
             7500.00
         );
