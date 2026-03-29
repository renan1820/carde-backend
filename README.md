# CARDE API

Backend do **Museu CARDE** — maior museu de veículos da América Latina (Contagem/MG).

## Stack

- Java 21 + Spring Boot 3.3
- PostgreSQL (Neon)
- Flyway (migrations)
- Docker / Koyeb

## Ambientes

| Ambiente | Branch | Banco |
|---|---|---|
| Local | qualquer | Docker Compose (`localhost:5432`) |
| Homologação | `develop` | Neon branch `staging` |
| Produção | `main` | Neon branch `main` |

## Rodar localmente

```bash
# 1. Subir PostgreSQL
docker compose up -d

# 2. Rodar a aplicação (IntelliJ ou Maven)
# Run Configuration → SPRING_PROFILES_ACTIVE=dev
```

## Endpoints

```
GET /api/v1/vehicles           lista de veículos (paginada)
GET /api/v1/vehicles/{id}      detalhe do veículo
GET /api/v1/vehicles?category= filtro por categoria
GET /api/v1/events             lista de eventos
GET /api/v1/events?featured=   apenas eventos em destaque
GET /api/v1/health             health check
GET /api/v1/swagger-ui.html    documentação interativa
```

## Categorias de veículo

`car` · `motorcycle` · `truck` · `bus` · `racing` · `classic`
