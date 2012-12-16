require 'puppet'
require 'java'

class EnvironmentSpecificPuppeteer
  include Java::com.puppetlabs.puppeteer.CatalogSource
  
  def initialize(environment_name)
    Puppet.initialize_settings(['--environment', environment_name])
  end

  def catalog(node_name)
    Puppet::Resource::Catalog.indirection.find(node_name).to_pson
  end
end