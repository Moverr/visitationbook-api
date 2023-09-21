ALTER TABLE  visitationrequests
    RENAME datecreated TO date_created;

ALTER TABLE visitationrequests
    RENAME invtype TO inv_type;

ALTER TABLE  visitationrequests
    RENAME departmentid TO department_id;

ALTER TABLE  visitationrequests
    RENAME officeid TO office_id;

ALTER TABLE  visitationrequests
    RENAME guestid TO guest_id;

ALTER TABLE  visitationrequests
    RENAME hostid TO host_id;

ALTER TABLE visitationrequests
    RENAME createdby TO created_by;

ALTER TABLE  visitationrequests
    RENAME dateupdated TO date_updated;

ALTER TABLE  visitationrequests
    RENAME updatedby TO updated_by;

ALTER TABLE  visitationrequests
    ADD COLUMN start_date timestamp without time zone;

ALTER TABLE  visitationrequests
    ADD COLUMN end_date timestamp without time zone;