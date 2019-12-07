import models.Event

fun handleEvent(event: String, payload: Event) {
    if (event == "issues" && (payload.action == "opened" || payload.action == "labeled")) {
        val login = payload.repository!!.owner.login
        val repo = payload.repository!!.name
        val number = payload.issue!!.number
        val labelIds = payload.label!!.node_id

        // TODO: do graphql query

        // TODO: handle query result
        /*
const labels = [
...result.labels.filter(label => labelIds.includes(label.id)),
...result.labels.filter(label => !labelIds.includes(label.id))
];

const labelGroups = groupBy(labels, label =>
getGroup(result.config.groups, label.name)
);

const labelIdsToRemove = Object.entries(labelGroups)
.filter(([groupName]) => groupName !== "")
.flatMap(([, matchedLabels]) => matchedLabels.slice(1))
.map(label => label.id);

if (labelIdsToRemove.length === 0) {
return;
}
         */

        // TODO: handle label mutation
    }
}