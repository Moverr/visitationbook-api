ALTER TABLE "default".visitationrequests
    RENAME datecreated TO date_created;

ALTER TABLE "default".visitationrequests
    RENAME invtype TO inv_type;

ALTER TABLE "default".visitationrequests
    RENAME departmentid TO department_id;

ALTER TABLE "default".visitationrequests
    RENAME officeid TO office_id;

ALTER TABLE "default".visitationrequests
    RENAME guestid TO guest_id;

ALTER TABLE "default".visitationrequests
    RENAME hostid TO host_id;

ALTER TABLE "default".visitationrequests
    RENAME createdby TO created_by;

ALTER TABLE "default".visitationrequests
    RENAME dateupdated TO date_updated;

ALTER TABLE "default".visitationrequests
    RENAME updatedby TO updated_by;

ALTER TABLE "default".visitationrequests
    ADD COLUMN start_date timestamp without time zone;

ALTER TABLE "default".visitationrequests
    ADD COLUMN end_date timestamp without time zone;