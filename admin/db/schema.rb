# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20130917025345) do

  create_table "assets", force: true do |t|
    t.string  "asset"
    t.string  "name"
    t.integer "version", default: 0
  end

  create_table "component_fields", force: true do |t|
    t.string  "name"
    t.string  "value_type"
    t.integer "component_id"
  end

  create_table "components", force: true do |t|
    t.string "name"
  end

  create_table "entities", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "game_users", force: true do |t|
    t.string   "user"
    t.string   "pass"
    t.string   "roles"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "auth_token"
  end

  create_table "protobuf_fields", force: true do |t|
    t.string  "field_type"
    t.string  "value_type"
    t.string  "name"
    t.integer "protobuf_message_id"
  end

  create_table "protobuf_messages", force: true do |t|
    t.string "name"
  end

  create_table "rails_admin_histories", force: true do |t|
    t.text     "message"
    t.string   "username"
    t.integer  "item"
    t.string   "table"
    t.integer  "month",      limit: 2
    t.integer  "year",       limit: 8
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "rails_admin_histories", ["item", "table", "month", "year"], name: "index_rails_admin_histories", using: :btree

  create_table "servers", force: true do |t|
    t.string  "environment"
    t.string  "name"
    t.boolean "http_enabled"
    t.string  "http_host"
    t.integer "http_port"
    t.boolean "udp_enabled"
    t.string  "udp_host"
    t.integer "udp_port"
    t.boolean "udt_enabled"
    t.string  "udt_host"
    t.integer "udt_port"
    t.string  "akka_host"
    t.integer "akka_port"
    t.integer "status",       default: 0
    t.boolean "enabled",      default: false
    t.boolean "tcp_enabled",  default: false
    t.string  "tcp_host"
    t.integer "tcp_port"
  end

  create_table "settings", force: true do |t|
    t.boolean "mono_enabled",                      default: false
    t.string  "environment"
    t.string  "game_handler"
    t.string  "data_store"
    t.string  "couchbase_servers"
    t.integer "cache_write_interval"
    t.integer "cache_writes_per_second"
    t.integer "world_grid_size"
    t.integer "world_grid_cell_size"
    t.integer "singleton_manager_router_count"
    t.integer "singleton_manager_update_interval"
    t.string  "seeds"
    t.string  "auth_handler"
  end

  create_table "system_statuses", force: true do |t|
    t.integer "status",       default: 0
    t.string  "resolve_with"
    t.string  "reason"
  end

  create_table "users", force: true do |t|
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "users", ["email"], name: "index_users_on_email", unique: true, using: :btree
  add_index "users", ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true, using: :btree

end