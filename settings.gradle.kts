rootProject.name = "wttch-wcbs"
include("wcbs")
include("core")
include("wcbs-test")
include("web")
include("multi-datasource")
include("mybatis-common-query")
include("mybatis-autoconfigure")
include("logs")
include("iam-app")
include("wttch-common")
findProject(":wttch-common")?.name = "common"
