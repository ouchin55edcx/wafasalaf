    package com.ouchin.wafasalaf.entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.math.BigDecimal;
    import java.time.LocalDate;

    @Entity
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Table(name = "requests")
    public class Request {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(unique = true, updatable = false, nullable = false)
        private Long id;

        @NotNull(message = "Please provide the project name.")
        @Size(min = 2, max = 50, message = "Project name must be between 2 and 50 characters")
        @Column(length = 50, nullable = false)
        private String project_name;

        @NotNull(message = "The profession cannot be null")
        @Size(min = 2, max = 50, message = "Profession must be between 2 and 50 characters")
        @Column(length = 50, nullable = false)
        private String profession;

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")
        @Column(nullable = false)
        private int amount;

        @NotNull(message = "Duration cannot be null")
        @Column(nullable = false)
        @Min(value = 1, message = "Duration must be at least 1 month")
        private Integer duration;

        @NotNull(message = "Monthly payments cannot be null")
        @PositiveOrZero(message = "Monthly payments must be positive or zero")
        @Column(nullable = false)
        private float monthly_payments;

        @NotNull(message = "Email cannot be null")
        @Email(message = "Email should be valid")
        @Column(nullable = false, unique = true)
        private String email;

        @NotNull(message = "Phone number cannot be null")
        @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
        @Column(nullable = false, unique = true, length = 10)
        private String phone_number;

        @NotNull(message = "Civility cannot be null")
        @Column(nullable = false)
        private String civility;

        @NotNull(message = "First name cannot be null")
        @Size(min = 2, max = 15, message = "First name must be between 2 and 15 characters")
        @Column(nullable = false, length = 15)
        private String first_name;

        @NotNull(message = "Last name cannot be null")
        @Size(min = 2, max = 15, message = "Last name must be between 2 and 15 characters")
        @Column(nullable = false, length = 15)
        private String last_name;

        @NotNull(message = "CIN cannot be null")
        @Pattern(regexp = "^[A-Za-z0-9]{8}$", message = "CIN must be exactly 8 alphanumeric characters")
        @Column(nullable = false, length = 8)
        private String cin;

        @NotNull(message = "Date of birth cannot be null")
        @Past(message = "Date of birth must be in the past")
        @Column(nullable = false)
        private LocalDate date_of_birth;

        @NotNull(message = "Start date cannot be null")
        @PastOrPresent(message = "Start date must be in the past or present")
        @Column(nullable = false)
        private LocalDate start_date;

        @NotNull(message = "Monthly net income cannot be null")
        @PositiveOrZero(message = "Monthly net income must be positive or zero")
        @Column(nullable = false)
        private BigDecimal monthly_net_income;


        @NotNull(message = "Loan status cannot be null")
        @Column(nullable = false)
        private boolean has_current_loans;

        @PositiveOrZero(message = "Mortgage monthly payment must be positive or zero")
        @Column(nullable = true)
        private BigDecimal mortgage_monthly_payment;

        @PositiveOrZero(message = "Other loans monthly payment must be positive or zero")
        @Column(nullable = true)
        private BigDecimal other_loans_monthly_payment;








    }
