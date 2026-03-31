Você acabou de iniciar uma sessão no projeto CARDE.
Leia o contexto abaixo e confirme que entendeu o estado atual antes de qualquer ação.

## Projeto CARDE — Estado Atual

### Stacks
| Stack | Repo local | Deploy | Status |
|-------|-----------|--------|--------|
| Flutter (app) | E:\FlutterProjects\cardeapp | — | MVP rodando |
| Spring Boot (API) | E:\IntelijProjects\carde\carde-backend | Koyeb (develop branch) | Rodando |
| React (admin) | E:\NodeProjects\CARDE\carde-admin | Vercel (main branch) | Rodando |

### Serviços externos
| Serviço | URL/Acesso | Custo |
|---------|-----------|-------|
| API (Koyeb) | https://separate-carlita-redbalckaplicativos-c602a46c.koyeb.app | Free |
| Admin (Vercel) | https://carde-admin.vercel.app | Free |
| DB (Neon) | console.neon.tech | Free |
| CI/CD | github.com/renan1820/carde-backend/actions | Free |

### Segredos (Koyeb + GitHub Secrets)
- `JWT_SECRET` — chave HMAC-SHA512 para tokens JWT
- `ADMIN_PASSWORD` — senha do admin@carde.com.br
- `DATABASE_URL/USER/PASSWORD` — Neon PostgreSQL

### Branches e deploy
- `develop` → staging (Koyeb) via GitHub Actions
- `main` → produção (Koyeb) — não usado ainda

### Features prontas
- **Flutter**: Home, Acervo (10 veículos), QR Scanner, Ingressos (WebView), Mapa (placeholder), Splash
- **API**: GET /vehicles, GET /vehicles/:id, GET /events | Admin CRUD veículos + eventos + auth JWT
- **Admin web**: Login, CRUD veículos, CRUD eventos

### Cache (Caffeine — em memória)
- `vehicles` — lista paginada por categoria (TTL 30min, evictado em Create/Update/Delete)
- `vehicle-by-id` — veículo por ID (TTL 30min, evictado em Update/Delete)
- `events` — lista paginada (TTL 30min, evictado em Create/Update/Delete)
- LoginUseCase **não** é cacheado (segurança)

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

### Monitoramento de custos
- Neon: console.neon.tech → Project → Usage
- Koyeb: app.koyeb.com → Billing
- GitHub Actions: Settings → Billing (ilimitado para repo público)

Confirme que leu e pergunte qual tarefa o usuário quer executar.
