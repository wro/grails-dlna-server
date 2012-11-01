databaseChangeLog = {

	changeSet(author: "wrombaut (generated)", id: "1351800117092-1") {
		createTable(tableName: "media_source") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "media_sourcePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "location", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "wrombaut (generated)", id: "1351800117092-2") {
		createIndex(indexName: "location_unique_1351800117065", tableName: "media_source", unique: "true") {
			column(name: "location")
		}
	}

	changeSet(author: "wrombaut (generated)", id: "1351800117092-3") {
		createIndex(indexName: "name_unique_1351800117068", tableName: "media_source", unique: "true") {
			column(name: "name")
		}
	}
}
