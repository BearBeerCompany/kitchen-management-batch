# kitchen-management-batch
Batch project for testing orders loading: job reads from an external .csv file and insert order in orders_ack table (GSG db).

## Configuration

Inside the application properties file, you should configure the domains for categories and menu_items, defining ids and related names: ids represent the external one, i.e. the ones defined for categories and order inside GSG application and corresponding to externalId fields into the related models inside kitchen-management-server projects.

```yaml
app:
  domain:
    categories:
      # categories {id, names} map: define here your configuration
      1: panini
      # ...
    menu-items:
      # menu items {id, names} map: define here your configuration
      # ...
```
