package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  @Override
  protected Set delegate() {
    return delegate;
  }

  public Groups withAdded(GroupData group) {
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups without(GroupData group) {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }

  public Groups(Collection<GroupData> groups){
    this.delegate = new HashSet<GroupData>(groups);
  }

  public Groups withReplaced(GroupData deleted, GroupData added){
    Groups groups = new Groups(this);
    groups.remove(deleted);
    groups.add(added);
    return groups;
  }

  public Groups withAddedContact(GroupData selectedGroup, ContactData modifiedContact) {
    Groups groups = new Groups(this);
    groups.remove(selectedGroup);
    selectedGroup.addContact(modifiedContact);
    groups.add(selectedGroup);
    return groups;
  }

  public Groups withRemovedContact(GroupData selectedGroup, ContactData modifiedContact) {
    Groups groups = new Groups(this);
    groups.remove(selectedGroup);
    selectedGroup.deleteContact(modifiedContact);
    groups.add(selectedGroup);
    return groups;
  }
}
