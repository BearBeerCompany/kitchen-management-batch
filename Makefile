# =========================================================
# Kitchen Management Batch - Makefile
# =========================================================

APP_NAME := kitchen-management-batch
VERSION  := 1.0.0
JAR      := target/$(APP_NAME)-$(VERSION).jar

MVN := mvn -B

.DEFAULT_GOAL := help

# ---------------------------------------------------------
# Help
# ---------------------------------------------------------

help: ## Show available commands
	@echo ""
	@echo "🍳 Kitchen Management Batch"
	@echo "--------------------------------------------------"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | \
	awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2}'
	@echo ""

# ---------------------------------------------------------
# Build & Run
# ---------------------------------------------------------

build: ## Clean and package jar
	$(MVN) clean package

run: build ## Build then run the batch
	java -jar $(JAR)

package: ## Package without cleaning
	$(MVN) package

clean: ## Clean target directory
	$(MVN) clean

# ---------------------------------------------------------
# Testing
# ---------------------------------------------------------

test: ## Run all tests
	$(MVN) test

coverage: ## Run tests with JaCoCo report
	$(MVN) verify
	@echo "Coverage report → target/site/jacoco/index.html"

# ---------------------------------------------------------
# Formatting (Spotless)
# ---------------------------------------------------------

format: ## Auto format code (Spotless apply)
	$(MVN) spotless:apply

lint: ## Check formatting only (Spotless check)
	$(MVN) spotless:check

# ---------------------------------------------------------
# CI / Dev helpers
# ---------------------------------------------------------

check: lint test ## Run lint + tests (good for CI)

all: clean build test ## Full pipeline

rebuild: clean run ## Clean rebuild and run
