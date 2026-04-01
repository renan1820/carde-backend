Você acabou de iniciar uma sessão no projeto CARDE.
Leia o contexto abaixo e confirme que entendeu o estado atual antes de qualquer ação.

## Projeto CARDE — Estado Atual

### Stacks
| Stack | Repo local | Deploy | Status |
|-------|-----------|--------|--------|
| Flutter (app) | E:\FlutterProjects\cardeapp | — | MVP rodando |
| Spring Boot (API) | E:\IntelijProjects\carde\carde-backend | Koyeb (develop branch) | Rodando |
| React (admin) | E:\NodeProjects\CARDE\carde-admin | Vercel (master branch) | Rodando |

### Serviços externos
| Serviço | URL/Acesso | Custo |
|---------|-----------|-------|
| API (Koyeb) | https://separate-carlita-redbalckaplicativos-c602a46c.koyeb.app | Free tier (hiberna) |
| Admin (Vercel) | https://carde-admin.vercel.app | Free |
| DB (Neon) | console.neon.tech | Free |
| Imagens (Cloudinary) | cloud: dbcu9rj00, preset: carde_vehicles (Unsigned) | Free 25GB |
| CI/CD | github.com/renan1820/carde-backend/actions | Free |

### Segredos (Koyeb + GitHub Secrets)
- `JWT_SECRET` — chave HMAC-SHA512 para tokens JWT
- `ADMIN_PASSWORD` — senha do admin@carde.com.br
- `DATABASE_URL/USER/PASSWORD` — Neon PostgreSQL

### Variáveis de ambiente — carde-admin (Vercel Dashboard)
- `VITE_API_BASE_URL` = https://separate-carlita-redbalckaplicativos-c602a46c.koyeb.app/api/v1
- `VITE_CLOUDINARY_CLOUD_NAME` = dbcu9rj00
- `VITE_CLOUDINARY_UPLOAD_PRESET` = carde_vehicles
- Arquivo local: E:\NodeProjects\CARDE\carde-admin\.env (não commitado)

### Branches e deploy
- `develop` → staging (Koyeb) via GitHub Actions
- `master` → produção (Vercel) — carde-admin
- `main` → produção (Koyeb) — não usado ainda

### Features prontas

**Flutter app:**
- Home, Acervo (10 veículos mock v001–v010), QR Scanner, Ingressos (WebView), Mapa (placeholder), Splash
- Vehicle tem `imageUrls: List<String>` (0/1/N imagens) com PageView + dots no detalhe
- Som do motor (engineSoundUrl opcional) com EngineSoundPlayer
- Dados mockados em `features/collection/data/mock_vehicles.dart`

**API Spring Boot:**
- GET /api/v1/vehicles, GET /api/v1/vehicles/:id
- GET /api/v1/events
- Admin CRUD veículos + eventos + auth JWT
- Cache Caffeine (TTL 30min): vehicles, vehicle-by-id, events
- Imagens em tabela separada `vehicle_images` (V7 migration)
- `specs` armazenado como JSON no `vehicles` table (Map<String,String>)
- Flyway migrations: V1–V8 aplicadas

**Admin React (Vite + TypeScript):**
- Login, CRUD veículos, CRUD eventos
- Upload multi-imagem via Cloudinary (unsigned preset)
- `specs` enviado como `Record<string, string>` (Map JSON)
- `VehicleRequest.specs: Record<string, string>` ← não array
- Tela de ConnectionError com retry quando API não responde
- `isNetworkError()` em `src/api/client.ts`

### Schema crítico — vehicle_images
```sql
CREATE TABLE vehicle_images (
  id BIGSERIAL PRIMARY KEY,
  vehicle_id VARCHAR(36) NOT NULL REFERENCES vehicles(id) ON DELETE CASCADE,
  url VARCHAR(1000) NOT NULL,
  sort_order INTEGER NOT NULL DEFAULT 0  -- INTEGER, não SMALLINT (Hibernate valida)
);
```

### Tipos críticos — carde-admin
```typescript
// Vehicle (resposta GET)
specs: Record<string, string>  // objeto/Map

// VehicleRequest (POST/PUT)
specs: Record<string, string>  // objeto/Map — NÃO array
imageUrls: string[]             // array de URLs Cloudinary
```

### Comandos frequentes
```bash
# Backend
cd E:/IntelijProjects/carde/carde-backend
mvn test
mvn spring-boot:run

# Frontend admin
cd E:/NodeProjects/CARDE/carde-admin
npm run dev

# Flutter
cd E:/FlutterProjects/cardeapp
flutter run -d android
dart run build_runner build --delete-conflicting-outputs
```

### Armadilhas conhecidas
- `sort_order` DEVE ser INTEGER (não SMALLINT) — Hibernate @OrderColumn falha no validate
- `specs` no PUT DEVE ser `{"chave": "valor"}` — não array
- axios pinado em 1.7.9 (1.14.x tem dep inexistente `plain-crypto-js`)
- Cloudinary preset deve ser "Unsigned" — sem isso upload retorna 4xx
- Koyeb free tier hiberna — primeira requisição pode demorar ~30s (ConnectionError screen cobre isso)

### Monitoramento de custos
- Neon: console.neon.tech → Project → Usage
- Koyeb: app.koyeb.com → Billing
- Cloudinary: console.cloudinary.com → Dashboard → Usage
- GitHub Actions: Settings → Billing (ilimitado para repo público)

Confirme que leu e pergunte qual tarefa o usuário quer executar.
