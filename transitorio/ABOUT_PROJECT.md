Reglas que vamos a seguir para tus Models (OBLIGATORIAS)

Estas son específicas para tu caso SISPRO:

Tipos
Legacy	Model
double IDs	Long
double montos	BigDecimal
varchar	String
datetime	LocalDateTime
bit	Boolean
longtext	@Lob String
PK compuestas

Siempre se modelan con:

@Embeddable

@EmbeddedId

Nunca con múltiples @Id.

Relaciones

NO se declaran @ManyToOne aún.
Solo campos simples.

(El integrador no valida relaciones todavía, solo transporta y limpia datos.)

Convención de nombres (importante)

Para no romper nada con ERP ni SISPRO:

Elemento	Convención
Clase	PascalCase
Campos	camelCase
Columnas	nombre exacto DB
Tabla	nombre exacto DB