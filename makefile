# Makefile for Java Project with JUnit 5 Testing
#
# This Makefile automates the build, test, installation of dependencies, and clean processes for the Task Java project.
# It compiles Java source and test files, runs JUnit tests using the JUnit 5 framework, and installs required dependencies.
#
# Note: This was written for Linux. Change RM_RF and MKDIR_P if you are running make on another OS
#
# Created by: Stephen Chrn
# Date: 22-Sept-24
# Version: 1.0

# Platform-specific commands
RM_RF = rm -rf # Windows would use `del /S /Q` or `Remove-Item -Recurse -Force` for PowerShell
MKDIR_P = mkdir -p # Windows would use just `mkdir` or `New-Item -ItemType Directory -Force` for PowerShell

# Variables
JAVAC = javac
JAVA = java
JAR_DIR = ./jar
JUNIT_JUPITER_API_JAR = junit-jupiter-api-5.11.0.jar
JUNIT_JUPITER_ENGINE_JAR = junit-jupiter-engine-5.11.0.jar
JUNIT_PLATFORM_CONSOLE_JAR = junit-platform-console-standalone-1.11.0.jar
CLASSPATH = .:$(JAR_DIR)/$(JUNIT_JUPITER_API_JAR):$(JAR_DIR)/$(JUNIT_JUPITER_ENGINE_JAR):$(BUILD_DIR)
SRC_DIR = src
TEST_DIR = test
BUILD_DIR = build

# URLs for dependencies
JUNIT_JUPITER_API_URL = https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-api/5.11.0/$(JUNIT_JUPITER_API_JAR)
JUNIT_JUPITER_ENGINE_URL = https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.11.0/$(JUNIT_JUPITER_ENGINE_JAR)
JUNIT_PLATFORM_CONSOLE_URL = https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.11.0/$(JUNIT_PLATFORM_CONSOLE_JAR)

# Source files
SRC_FILES = $(wildcard $(SRC_DIR)/*.java)
TEST_FILES = $(wildcard $(TEST_DIR)/*.java)

# Output class files
SRC_CLASSES = $(patsubst $(SRC_DIR)/%.java, $(BUILD_DIR)/%.class, $(SRC_FILES))
TEST_CLASSES = $(patsubst $(TEST_DIR)/%.java, $(BUILD_DIR)/%.class, $(TEST_FILES))

# Default target
# This target installs dependencies, compiles the source and test files, then runs the tests.
all: install compile run-tests

# Install dependencies
# This target downloads the required JAR files using curl.
install:
	@$(MKDIR_P) $(JAR_DIR)
	curl -o $(JAR_DIR)/$(JUNIT_JUPITER_API_JAR) $(JUNIT_JUPITER_API_URL)
	curl -o $(JAR_DIR)/$(JUNIT_JUPITER_ENGINE_JAR) $(JUNIT_JUPITER_ENGINE_URL)
	curl -o $(JAR_DIR)/$(JUNIT_PLATFORM_CONSOLE_JAR) $(JUNIT_PLATFORM_CONSOLE_URL)

# Compile source and test files
# This target compiles both the source and test Java files into class files in the build directory.
compile: $(SRC_CLASSES) $(TEST_CLASSES)

# Compile individual source files
# This rule compiles Java source files into class files.
$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java
	@$(MKDIR_P) $(BUILD_DIR)
	$(JAVAC) -d $(BUILD_DIR) -sourcepath $(SRC_DIR) $<

# Compile individual test files
# This rule compiles Java test files with the required classpath dependencies.
$(BUILD_DIR)/%.class: $(TEST_DIR)/%.java
	@$(MKDIR_P) $(BUILD_DIR)
	$(JAVAC) -cp $(CLASSPATH) -d $(BUILD_DIR) -sourcepath $(SRC_DIR):$(TEST_DIR) $<

# Run JUnit tests
# This target runs the JUnit tests using the JUnit Platform Console.
run-tests: compile
	$(JAVA) -jar $(JAR_DIR)/$(JUNIT_PLATFORM_CONSOLE_JAR) --classpath=$(BUILD_DIR) --select-class=AppointmentTest --select-class=AppointmentServiceTest --select-class=ContactTest --select-class=ContactServiceTest --select-class=TaskTest --select-class=TaskServiceTest

# Clean build directory
# This target removes all generated class files and the build directory.
clean:
	$(RM_RF) $(BUILD_DIR)

# Phony targets
# These targets are not associated with files and will always run when called.
.PHONY: all install compile run-tests clean

