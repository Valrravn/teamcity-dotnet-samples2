package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.DotnetTestStep
import jetbrains.buildServer.configs.kotlin.buildSteps.dotnetTest
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'LinuxTests'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("LinuxTests")) {
    params {
        add {
            param("teamcity.tool.JetBrains.dotCover.DotNetCliTool.2020.2.2", "2020.2.2")
        }
    }

    expectSteps {
        dotnetTest {
            name = "Test"
            projects = "Clock.Tests/Clock.Tests.csproj"
            coverage = dotcover {
                toolPath = "%teamcity.tool.JetBrains.dotCover.DotNetCliTool.2020.2.2%"
                assemblyFilters = "+:Clock"
                attributeFilters = "-:Clock.ExcludeFromCodeCoverageAttribute"
            }
            dockerImage = "mcr.microsoft.com/dotnet/sdk:5.0"
            dockerImagePlatform = DotnetTestStep.ImagePlatform.Linux
        }
    }
    steps {
        update<DotnetTestStep>(0) {
            clearConditions()
            param("dotNetCoverage.tool", "")
        }
    }
}