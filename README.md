
flowchart TD
    A["Client (Postman)"] --> B["Spring Security Filter Chain"]
    B --> C["HTTP Basic Authentication"]
    C --> D["AuthenticationManager"]
    D --> E["DaoAuthenticationProvider"]
    E --> F["MyUserDetailsService"]
    F --> G["IUserRepo"]
    G --> H[("MySQL Database")]
    H --> G
    G --> F
    F --> I["UserPrincipal"]
    I --> E
    E --> J{"Authentication successful?"}
    J -->|Yes| K["Access REST Controller"]
    J -->|No| L["401 Unauthorized"]
