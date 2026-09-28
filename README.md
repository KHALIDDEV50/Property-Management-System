🏢 Property Management System

A Property Management System (PMS) built with Java Spring Boot to manage properties, units, owners, tenants, contracts, payment schedules, and subscriptions through a centralized RESTful API.

The system also includes Business Extra Points, AI-powered management reports using OpenAI API, and HTML email integration using Gmail SMTP.

📌 Project Overview

Property management can involve a large amount of data related to:

Properties
Units
Owners
Tenants
Contracts
Payment schedules
Management office subscriptions

Managing these operations manually can lead to scattered data, repetitive work, and difficulties generating clear reports.

This project provides a centralized system that organizes the main property management operations and automates several business processes.

🎯 Project Goals

The main goals of the system are:

Centralize property management data.
Manage properties and rental units.
Manage owners and tenants.
Create and manage rental contracts.
Manage payment schedules.
Automate the generation of upcoming payments.
Manage subscription plans for management offices.
Transfer property ownership.
Generate management reports.
Use AI to analyze system data.
Send reports through email.

👥 System Users
🏢 Property Management Offices

Management offices can manage:

Properties
Units
Owners
Tenants
Contracts
Payments
Subscriptions
Reports
👤 Property Owners

Owners can be represented in the system and linked to their properties.

👨‍💼 Management Teams

Management teams can follow:

Unit status
Contract status
Payment status
Property information
Management reports
🗂️ Main Entities

The system contains the following main entities:

ManagementOffice
Owner
Property
Unit
Tenant
Contract
PaymentSchedule
Subscription

⚙️ Technologies
Technology	Usage
Java	Main programming language
Spring Boot	Backend framework
Spring Web	REST APIs
Spring Data JPA	Database access
Hibernate	ORM
Jakarta Validation	Input validation
MySQL	Database
Maven	Dependency management
Postman	API testing
OpenAI API	AI reports
Gmail SMTP	Email sending
IntelliJ IDEA	Development environment


🏗️ System Architecture
Controller

Responsible for:

Receiving HTTP requests.
Handling API endpoints.
Validating request data.
Returning HTTP responses.
Service

Contains the main business logic of the system.

Examples:

Contract renewal.
Payment generation.
Property ownership transfer.
Subscription renewal.
Remaining contract days calculation.
Repository

Responsible for database access using Spring Data JPA.

Database

Stores the application's property management data using MySQL.

🔌 REST API

The project provides CRUD APIs for the main entities.

The main operations include:

GET
POST
PUT
DELETE

Examples:

GET    /api/v1/unit/get
POST   /api/v1/unit/add
PUT    /api/v1/unit/update/{unitId}
DELETE /api/v1/unit/delete/{unitId}
⭐ Business Extra Points

The project includes additional business features beyond the basic CRUD operations.

1. Unit Management
Available Units
GET /api/v1/unit/available

Returns units with:

status = AVAILABLE
Occupied Units
GET /api/v1/unit/occupied

Returns occupied units.

Available Units by Type
GET /api/v1/unit/available/type/{unitType}

Example:

GET /api/v1/unit/available/type/APARTMENT
Change Unit Status
PUT /api/v1/unit/status/{unitId}/{status}

Example:

PUT /api/v1/unit/status/13/OCCUPIED
📋 2. Contract Management
Renew Contract
PUT /api/v1/contract/renew/{contractId}/{newEndDate}

Updates the contract end date and activates the contract.

Expire Contract
PUT /api/v1/contract/expire/{contractId}/{endDate}

Changes the contract status to:

EXPIRED
Cancel Contract
PUT /api/v1/contract/cancel/{contractId}

Changes the contract status to:

CANCELLED
Calculate Remaining Days
GET /api/v1/contract/remaining-days/{contractId}

Calculates the number of days remaining until the contract end date.

Calculate Total Contract Rent
GET /api/v1/contract/total-rent/{contractId}

Calculates the total rent based on:

Rent amount
Payment frequency
Contract start date
Contract end date
Check Contract Expiration
PUT /api/v1/contract/check-expiration/{contractId}

Checks whether the contract has expired and updates its status accordingly.

💰 3. Payment Automation
Update Payment Status
PUT /api/v1/payment-schedule/status/{paymentScheduleId}/{status}

Example:

PUT /api/v1/payment-schedule/status/50/PAID

When the payment becomes PAID, the system records the payment date.

Generate Next Payment
POST /api/v1/payment-schedule/generate/{contractId}

The system:

Finds the contract.
Reads the rent amount.
Reads the payment frequency.
Finds the latest payment schedule.
Calculates the next due date.
Creates the next payment automatically.
Sets the new payment status to PENDING.

Supported payment frequencies include:

MONTHLY
QUARTERLY
SEMI_ANNUAL
ANNUAL

This reduces the need to manually create every future payment.

🏠 4. Property Ownership Transfer

The system supports transferring a property from one owner to another.

PUT /api/v1/property/transfer-owner/{propertyId}/{newOwnerId}

Example:

PUT /api/v1/property/transfer-owner/5/12

The system updates the property's ownerId to the new owner.

💳 5. Subscription Plans

Management offices can manage their subscription plans.

Change Subscription Plan
PUT /api/v1/subscription/change-plan/{subscriptionId}/{plan}/{price}
Renew Subscription
PUT /api/v1/subscription/renew/{subscriptionId}/{newEndDate}
🤖 AI Management Report

One of the main additional features is the AI Management Report.

The system collects data from the PMS and sends the information to the OpenAI API for analysis.

AI Report Endpoint
GET /api/v1/report/ai

The generated report is returned as HTML and contains sections such as:

Executive Summary
KPIs
Portfolio Overview
Unit Analysis
Contract Analysis
Payment Analysis
Subscription Analysis
Observations
Missing Data
Recommendations
Visual Charts

The report is designed for Arabic and RTL presentation.

📧 Email Integration

The project integrates with Gmail SMTP to send emails from the application.

The implementation uses:

Spring Boot Starter Mail
JavaMailSender
MimeMessage
MimeMessageHelper

HTML email content is supported using:

helper.setText(htmlMessage, true);

The true value indicates that the email body should be interpreted as HTML.

📩 Send Email

The system provides an email endpoint:

POST /api/v1/email/send

Parameters:

to
subject
message

The AI report can also be generated and sent directly through email.

📊 Report Data

The management report can include statistics such as:

Number of management offices
Number of owners
Number of properties
Number of units
Number of tenants
Number of contracts
Unit status distribution
Contract status distribution
Payment status distribution
Contract rent amounts
Payment amounts

The AI report only uses the data provided by the system and does not rely on external property data.

✅ Validation

The system uses Jakarta Validation to validate incoming data.

Examples include:

@NotNull
@NotEmpty
@Positive
@Size
@Pattern
@Email
@Min

Validation helps prevent invalid data from being stored in the database.

🗄️ Database

The project uses:

MySQL

Database:

property_management_system

Main tables:

management_office
owner
property
unit
tenant
contract
payment_schedule
subscription


